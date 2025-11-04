package com.danielflores.localroomapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielflores.localroomapp.models.Cronos

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.room
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 09:41
 * All rights reserved 2025.
 **/

@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDatabase: RoomDatabase() {
    abstract fun cronosDao(): CronosDatabaseDao
}