package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.UserPrefsRepository

class GetUserPreferencesUseCase(
    private val repository: UserPrefsRepository
) {

    suspend operator fun invoke(): UserPreferences {
        return repository.getUserPrefs(UserPreferences.NAME) ?: UserPreferences()
    }
}