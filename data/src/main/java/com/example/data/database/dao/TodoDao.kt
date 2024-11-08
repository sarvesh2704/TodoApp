package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.tables.TodoTable

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoTable: TodoTable): Long

    @Query("select * from todo_detail")
    suspend fun getTodoDetails(): List<TodoTable>

}
