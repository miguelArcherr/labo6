package com.falconteam.laboratorio_5.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falconteam.laboratorio_5.data.database.daos.PostDao
import com.falconteam.laboratorio_5.data.database.entities.PostEntity

@Database(entities = [PostEntity::class], version = 2)
abstract class PostDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}