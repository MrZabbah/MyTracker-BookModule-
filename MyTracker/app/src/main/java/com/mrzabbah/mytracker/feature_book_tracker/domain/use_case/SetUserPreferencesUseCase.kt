package com.mrzabbah.mytracker.feature_book_tracker.domain.use_case

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.UserPrefsRepository

class SetUserPreferencesUseCase(
    private val repository: UserPrefsRepository
) {

    suspend operator fun invoke(userPreferences: UserPreferences) {
        repository.setUserPrefs(userPreferences)
    }
}