package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

data class BookTrackerUseCases(
    val getUserBooksUseCase: GetUserBooksUseCase,
    val deleteUserBookUseCase: DeleteUserBookUseCase,
    val addUserBookUseCase: AddUserBookUseCase,
    val getBookSearchUseCase: GetBookSearchUseCase
)
