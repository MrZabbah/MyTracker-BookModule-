package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

data class BookTrackerUseCases(
    val getUserBooksUseCase: GetUserBooksUseCase,
    val getUserBookByIdUseCase: GetUserBookByIdUseCase,
    val deleteUserBookUseCase: DeleteUserBookUseCase,
    val addUserBookUseCase: AddUserBookUseCase,
    val updateUserBookUseCase: UpdateUserBookUseCase,
    val getBookSearchUseCase: GetBookSearchUseCase,
    val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    val setUserPreferencesUseCase: SetUserPreferencesUseCase
)
