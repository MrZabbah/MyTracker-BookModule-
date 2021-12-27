package com.mrzabbah.mytracker.feature_book_tracker.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzabbah.mytracker.core.util.Resource
import com.mrzabbah.mytracker.feature_book_tracker.domain.use_case.BookTrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookTrackerUseCases: BookTrackerUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        getSearchBooks("el camino de los reyes") // habra que crear un evento al escribir y que se lance, also un job pa cancelar el previo
    }

    fun onEvent() {

    }

    private fun getSearchBooks(title: String? = null, author: String? = null) {
        bookTrackerUseCases.getBookSearchUseCase(title, author)
            .onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
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
