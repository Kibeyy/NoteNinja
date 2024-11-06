package com.example.noteninja.di

import android.content.Context
import androidx.room.Room
import com.example.noteninja.room.NoteDao
import com.example.noteninja.room.AppDatabase
import com.example.noteninja.room.NoteRepository
import com.example.noteninja.viewmodel.NotesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //provisions for the notes
    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: AppDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao) = NoteRepository(noteDao)

    @Singleton
    @Provides
    fun provideNoteViewModel(noteRepository: NoteRepository) = NotesViewModel(noteRepository)

    //provisions for the todos

    @Singleton
    @Provides
    fun provideTodoDao(noteDatabase: AppDatabase) = noteDatabase.todoDao()


    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "User_db",
    ).fallbackToDestructiveMigration()
        .build()
}