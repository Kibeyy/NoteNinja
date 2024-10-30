package com.example.noteninja.di

import android.content.Context
import androidx.room.Room
import com.example.noteninja.room.NoteDao
import com.example.noteninja.room.NoteDatabase
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

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao) = NoteRepository(noteDao)

    @Singleton
    @Provides
    fun provideNoteViewModel(noteRepository: NoteRepository) = NotesViewModel(noteRepository)

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "User_db",
    ).fallbackToDestructiveMigration()
        .build()
}