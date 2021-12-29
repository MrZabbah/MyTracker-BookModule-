package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.use_case.BookTrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificBookViewModel @Inject constructor(
    private val bookTrackerUseCases: BookTrackerUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SpecificBookState())
    val state: State<SpecificBookState> = _state

    private val _eventFlow = MutableSharedFlow<SpecificBookViewModel.UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    book = bookTrackerUseCases.getUserBookByIdUseCase(id)
                )
            }
        }
    }

    fun onEvent(event: SpecificBookEvent) {
        when (event) {
            is SpecificBookEvent.TogglePagesDialog -> {
                _state.value = state.value.copy(
                    isDialogToggled = !state.value.isDialogToggled
                )
            }
            is SpecificBookEvent.ClickReadButton -> {
                state.value.book?.let { manageReadTime(it) }
            }
            is SpecificBookEvent.FilterLabel -> {
                val newLabel = if (event.label != state.value.book?.label)
                    event.label
                else Book.nonLabelColor

                val updatedBook = state.value.book?.copy(
                    label = newLabel
                )
                updatedBook?.let { updateBook(it) }
            }
            is SpecificBookEvent.ToggleLabelSection -> {
                _state.value = state.value.copy(
                    isFilterLabelToggled = !state.value.isFilterLabelToggled
                )
            }
            is SpecificBookEvent.ClickOnConfirmPage -> {
                if (event.currPage < 0 || event.currPage > state.value.book?.pageCount!!) {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Incorrect page number. Please try again"))
                    }
                } else {
                    val updatedBook = state.value.book?.copy(
                        currentPage = event.currPage
                    )
                    updatedBook?.let { updateBook(it) }
                }
            }
        }
    }

    private fun manageReadTime(book: Book) {
        val time = book.lastReadTimestamp?.let { startedTime ->
            System.currentTimeMillis() - startedTime
        } ?: 0L

        val updatedBook = book.copy(
            readTime = book.readTime + time,
            lastReadTimestamp = if (time <= 0L) System.currentTimeMillis() else null
        )

        updateBook(updatedBook)
    }

    private fun updateBook(book: Book) {
        _state.value = state.value.copy(
            book = book
        )

        viewModelScope.launch {
            bookTrackerUseCases.updateUserBookUseCase(book)
        }
    }
}