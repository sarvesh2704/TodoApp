package com.example.domain.usecase

import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import com.example.domain.model.TodoModel
import kotlinx.coroutines.flow.flow

class InsertTodoUseCase(
    private val todoRepository: TodoRepository,
    private val todoTableMapper: TodoTableMapper
) {

    fun insertData(todoContent: String) = flow {
        emit(todoRepository.insertTodoDetail(todoTableMapper.mapToEntity(TodoModel(todoContent = todoContent))))
    }

}
