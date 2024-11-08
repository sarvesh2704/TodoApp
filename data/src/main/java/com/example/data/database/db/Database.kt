package com.example.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TodoDao
import com.example.data.database.tables.TodoTable

@Database (entities = [TodoTable::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        val DATABASE_NAME: String = "todo_db"

    }

}