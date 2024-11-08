package com.example.data.repository

import com.example.data.database.dao.TodoDao
import com.example.data.database.tables.TodoTable

class TodoRepository
constructor(
    private val todoDao: TodoDao
) {
    suspend fun insertTodoDetail(todoTableData: TodoTable): Long {
        return todoDao.insert(todoTableData)
    }

    suspend fun getAllTodoDetails(): List<TodoTable> {
        return todoDao.getTodoDetails()
    }
}
