package com.mrzabbah.mytracker.feature_book_tracker.data.repository

import com.mrzabbah.mytracker.feature_book_tracker.data.data_source.UserPrefsDao
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.UserPrefsRepository
import javax.inject.Inject

class UserPrefsRepositoryImpl @Inject constructor(
    private val dao: UserPrefsDao
): UserPrefsRepository {

    override suspend fun getUserPrefs(key: String): UserPreferences? {
        return dao.getUserPrefs(key)
    }

    override suspend fun setUserPrefs(userPrefs: UserPreferences) {
        dao.setUserPrefs(userPrefs)
    }
}