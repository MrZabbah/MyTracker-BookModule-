package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzabbah.mytracker.core.util.Resource
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.InvalidBookException
import com.mrzabbah.mytracker.feature_book_tracker.domain.use_case.BookTrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    init {
        savedStateHandle.get<String>("query")?.let { query ->
            getSearchBooks(query) // habra que crear un evento al escribir y que se lance, also un job pa cancelar el previo
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
                    } catch (e: InvalidBookException) {
                        println("NO BUENO DE BOOK")
                    }
                }
            }
        }
    }

    private fun getSearchBooks(title: String? = null, author: String? = null) {
        getSearchBooksJob?.cancel()
        getSearchBooksJob = bookTrackerUseCases.getBookSearchUseCase(title, author)
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
                            lastSearch = title ?: ""
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
