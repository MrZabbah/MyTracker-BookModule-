package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.UserPreferences

@Database(
    entities = [Book::class, UserPreferences::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BookDatabase: RoomDatabase() {

    abstract val bookDao: BookDao
    abstract val userPrefsDao: UserPrefsDao

    companion object {
        const val DATABASE_NAME = "books_db"
    }
}