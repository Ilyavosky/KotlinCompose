package com.ilya.examen.di


import android.content.Context
import androidx.room.Room
import com.ilya.examen.data.FormDatabase
import com.ilya.examen.data.StoreDarkMode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provee el DataStore del Tema
    @Provides
    @Singleton
    fun provideStoreDarkMode(@ApplicationContext context: Context): StoreDarkMode {
        return StoreDarkMode(context)
    }

    // Provee la base de datos Room
    @Provides
    @Singleton
    fun provideFormDatabase(@ApplicationContext context: Context): FormDatabase {
        return Room.databaseBuilder(
            context,
            FormDatabase::class.java,
            "form_database"
        ).build()
    }

    // Provee el DAO de Room (usando la base de datos que ya proveímos)
    @Provides
    @Singleton
    fun provideFormDao(database: FormDatabase) = database.formDao()
}