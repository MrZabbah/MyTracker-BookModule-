package com.mrzabbah.mytracker.feature_book_tracker.presentation.specificBook

sealed class SpecificBookEvent {
    data class FilterLabel(val label: Int): SpecificBookEvent()
    data class ClickOnConfirmPage(val currPage: Int): SpecificBookEvent()
    object ClickReadButton: SpecificBookEvent()
    object TogglePagesDialog: SpecificBookEvent()
    object ToggleLabelSection: SpecificBookEvent()
    object ToggleActive: SpecificBookEvent()
}
