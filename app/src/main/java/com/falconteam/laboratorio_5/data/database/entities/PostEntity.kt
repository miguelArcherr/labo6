package com.falconteam.laboratorio_5.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import com.falconteam.laboratorio_5.data.remote.services.Message


@Entity(tableName = "table_post")
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String,
    val author: String,
    val comments: List<Message>
)