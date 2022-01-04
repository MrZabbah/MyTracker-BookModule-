package com.mrzabbah.mytracker.feature_book_tracker.data.data_source

import androidx.room.*
import com.mrzabbah.mytracker.feature_book_tracker.domain.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    //@Query("SELECT * FROM book WHERE (CASE WHEN (authors IS NULL) THEN authors = authors ELSE authors = :author END) AND label IN (:labels)")
    @Query("SELECT * FROM book WHERE (:author IS :author AND label IN (:labels)) OR active IS 1")
    fun getUserBooks(author: String?, labels: List<Int>): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getUserBookById(id: String): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToUserBooks(book: Book)

    @Delete
    suspend fun deleteUserBook(book: Book)
}