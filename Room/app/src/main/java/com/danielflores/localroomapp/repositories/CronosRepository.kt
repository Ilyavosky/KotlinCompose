package com.danielflores.localroomapp.repositories

import com.danielflores.localroomapp.models.Cronos
import com.danielflores.localroomapp.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.repositories
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 10:00
 * All rights reserved 2025.
 **/
class CronosRepository @Inject constructor(private val cronosDatabaseDao: CronosDatabaseDao) {
    // Get all cronos
    fun  getAllCronos(): Flow<List<Cronos>> = cronosDatabaseDao.getCronos()
        .flowOn(Dispatchers.IO)
        .conflate()

    // Get a single crono by id
    fun getCronoById(key: Long): Flow<Cronos?> = cronosDatabaseDao.getCronosById(key)
        .flowOn(Dispatchers.IO)
        .conflate()

    // Insert a crono
    suspend fun insert(cronos: Cronos) = cronosDatabaseDao.insert(cronos)

    // Update a crono
    suspend fun update(cronos: Cronos) = cronosDatabaseDao.update(cronos)

    // Delete a crono
    suspend fun delete(cronos: Cronos) = cronosDatabaseDao.delete(cronos)
}