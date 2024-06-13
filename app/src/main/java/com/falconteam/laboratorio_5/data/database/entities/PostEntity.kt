package com.falconteam.laboratorio_5.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "table_post")
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val author: String
)