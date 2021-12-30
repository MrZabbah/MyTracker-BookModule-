package com.mrzabbah.mytracker.feature_book_tracker.di

import android.app.Application
import androidx.room.Room
import com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.GoogleBooksService
import com.mrzabbah.mytracker.feature_book_tracker.data.data_source.BookDatabase
import com.mrzabbah.mytracker.feature_book_tracker.data.repository.BookRepositoryImpl
import com.mrzabbah.mytracker.feature_book_tracker.data.repository.UserPrefsRepositoryImpl
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.BookRepository
import com.mrzabbah.mytracker.feature_book_tracker.domain.repository.UserPrefsRepository
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
    fun provideUserPrefsRepository(db: BookDatabase): UserPrefsRepository {
        return UserPrefsRepositoryImpl(db.userPrefsDao)
    }

    @Provides
    @Singleton
    fun provideBookUseCases(bookRepository: BookRepository, userPrefsRepository: UserPrefsRepository): BookTrackerUseCases {
        return BookTrackerUseCases(
            getUserBooksUseCase = GetUserBooksUseCase(bookRepository),
            getUserBookByIdUseCase = GetUserBookByIdUseCase(bookRepository),
            deleteUserBookUseCase = DeleteUserBookUseCase(bookRepository),
            addUserBookUseCase = AddUserBookUseCase(bookRepository),
            updateUserBookUseCase = UpdateUserBookUseCase(bookRepository),
            getBookSearchUseCase = GetBookSearchUseCase(bookRepository),
            getUserPreferencesUseCase = GetUserPreferencesUseCase(userPrefsRepository),
            setUserPreferencesUseCase = SetUserPreferencesUseCase(userPrefsRepository)
        )
    }
}