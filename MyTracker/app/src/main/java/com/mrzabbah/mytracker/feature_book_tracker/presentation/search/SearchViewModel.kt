package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzabbah.mytracker.core.util.Resource
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.InvalidBookException
import com.mrzabbah.mytracker.feature_book_tracker.domain.use_case.BookTrackerUseCases
import com.mrzabbah.mytracker.feature_book_tracker.domain.util.SearchMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookTrackerUseCases: BookTrackerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private var getSearchBooksJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }

    init {
        savedStateHandle.get<String>("searchMode")?.let { searchMode ->
            _state.value = state.value.copy(
                searchMode = SearchMode.parseFromString(searchMode)
            )
        }
        savedStateHandle.get<String>("query")?.let { query ->
            getSearchBooks(query)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                _state.value = state.value.copy(
                    books = emptyList()
                )
                getSearchBooks(event.title)
            }
            is SearchEvent.SaveBook -> {
                viewModelScope.launch {
                    try {
                        bookTrackerUseCases.addUserBookUseCase(event.book)
                        _eventFlow.emit(UiEvent.ShowSnackbar("\"${event.book.title}\" has been added to \"Your Books\""))
                    } catch (e: InvalidBookException) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Not possible to save. The book is corrupted"))
                    }
                }
            }
            SearchEvent.ChangeSearchMode -> {
                val newSearchMode = when(state.value.searchMode) {
                    SearchMode.Advanced -> SearchMode.ByTitle
                    SearchMode.ByAuthor -> SearchMode.Advanced
                    SearchMode.ByTitle -> SearchMode.ByAuthor
                }
                _state.value = state.value.copy(
                    searchMode = newSearchMode
                )
            }
        }
    }

    private fun getSearchBooks(rawSearch: String? = null) {

        getSearchBooksJob?.cancel()
        getSearchBooksJob = bookTrackerUseCases.getBookSearchUseCase(rawSearch, state.value.searchMode)
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = "",
                            lastSearch = rawSearch ?: ""
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            books = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}
