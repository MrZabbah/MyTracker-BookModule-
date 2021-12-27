package com.mrzabbah.mytracker.feature_book_tracker.di

import android.app.Application
import androidx.room.Room
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.GoogleBooksService
import com.mrzabbah.mytracker.feature_book_tracker.data.data_source.BookDatabase
import com.mrzabbah.mytracker.feature_book_tracker.data.repository.BookRepositoryImpl
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import com.mrzabbah.mytracker.feature_book_tracker.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideGoogleBooksService(): GoogleBooksService {
        return GoogleBooksService.create()
    }

    @Provides
    @Singleton
    fun provideBookDatabase(app: Application): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            BookDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookRepository(db: BookDatabase, api: GoogleBooksService): BookRepository {
        return BookRepositoryImpl(db.bookDao, api)
    }
    
    @Provides
    @Singleton
    fun provideBookUseCases(repository: BookRepository): BookTrackerUseCases {
        return BookTrackerUseCases(
            getUserBooksUseCase = GetUserBooksUseCase(repository),
            deleteUserBookUseCase = DeleteUserBookUseCase(repository),
            addUserBookUseCase = AddUserBookUseCase(repository),
            getBookSearchUseCase = GetBookSearchUseCase(repository)
        )
    }
}