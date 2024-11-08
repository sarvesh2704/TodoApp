package com.example.domain.usecase

import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import kotlinx.coroutines.flow.flow

class GetTodoUseCase(
    private val todoRepository: TodoRepository,
    private val todoTableMapper: TodoTableMapper
) {
    fun getTodoDetails() = flow {
        emit(todoTableMapper.mapFromEntityList(todoRepository.getAllTodoDetails()))
    }

}
