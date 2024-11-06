package com.example.todoapplication.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapplication.database.dao.TodoDao
import com.example.todoapplication.database.tables.TodoTable

@Database (entities = [TodoTable::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        val DATABASE_NAME: String = "todo_db"

    }

}