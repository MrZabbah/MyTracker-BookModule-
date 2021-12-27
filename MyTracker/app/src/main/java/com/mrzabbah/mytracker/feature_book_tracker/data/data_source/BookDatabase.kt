package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book

@Database(
    entities = [Book::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BookDatabase: RoomDatabase() {

    abstract val bookDao: BookDao

    companion object {
        const val DATABASE_NAME = "books_db"
    }
}