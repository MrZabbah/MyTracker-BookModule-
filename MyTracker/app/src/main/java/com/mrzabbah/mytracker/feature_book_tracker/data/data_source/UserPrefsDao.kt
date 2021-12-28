package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences

@Dao
interface UserPrefsDao {

    @Query("SELECT * FROM userpreferences WHERE `key` = :key")
    suspend fun getUserPrefs(key: String): UserPreferences?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setUserPrefs(userPrefs: UserPreferences)
}