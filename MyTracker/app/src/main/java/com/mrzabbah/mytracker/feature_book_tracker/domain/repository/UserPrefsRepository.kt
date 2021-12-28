package com.mrzabbah.mytracker.feature_book_tracker.domain.repository

import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences

interface UserPrefsRepository {

    suspend fun getUserPrefs(key: String): UserPreferences?

    suspend fun setUserPrefs(userPrefs: UserPreferences)
}