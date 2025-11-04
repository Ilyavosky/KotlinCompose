package com.danielflores.localroomapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Project: LocalRoomApp
 * Package: com.danielflores.localroomapp.models
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 09:37
 * All rights reserved 2025.
 **/


@Entity(tableName = "cronos")
data class Cronos(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo("crono")
    val crono: Long
)
