package com.danielflores.localroomapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.danielflores.localroomapp.models.Cronos
import kotlinx.coroutines.flow.Flow

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.room
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 09:42
 * All rights reserved 2025.
 **/

// Interface -> Repositorio -> ViewModel -> View
@Dao
interface CronosDatabaseDao {
    // Crud Operations
    @Query("SELECT * FROM cronos")
    fun getCronos(): Flow<List<Cronos>>

    @Query("SELECT * FROM cronos where id = :key")
    fun getCronosById(key: Long): Flow<Cronos?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cronos: Cronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cronos: Cronos)

    @Delete
    suspend fun delete(crono: Cronos)
}