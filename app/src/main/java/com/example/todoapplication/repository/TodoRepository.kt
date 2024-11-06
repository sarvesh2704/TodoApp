package com.example.todoapplication.repository

import com.example.todoapplication.database.dao.TodoDao
import com.example.todoapplication.database.mapper.TodoTableMapper
import com.example.todoapplication.model.TodoModel
import kotlinx.coroutines.flow.flow

class TodoRepository
constructor(
    private val todoTableMapper: TodoTableMapper,
    private val todoDao: TodoDao
) {

    suspend fun insertTodoDetail(todoContent: String) = flow {
        emit(todoDao.insert(todoTableMapper.mapToEntity(TodoModel(todoContent = todoContent))))
    }

    suspend fun getAllTodoDetails() = flow {
        val todoModel = todoTableMapper.mapFromEntityList(todoDao.getTodoDetails())
        emit(todoModel)
    }

}