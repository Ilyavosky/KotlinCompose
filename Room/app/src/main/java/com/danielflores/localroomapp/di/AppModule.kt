package com.danielflores.localroomapp.di

import android.content.Context
import androidx.room.Room
import com.danielflores.localroomapp.room.CronosDatabase
import com.danielflores.localroomapp.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.di
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 09:55
 * All rights reserved 2025.
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesCronosDao(cronosDatabase: CronosDatabase): CronosDatabaseDao {
        return cronosDatabase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context) : CronosDatabase {
        return Room.databaseBuilder(
            context,
            CronosDatabase::class.java,
            "cronos_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

}