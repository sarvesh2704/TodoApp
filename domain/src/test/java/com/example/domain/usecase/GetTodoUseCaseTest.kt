package com.example.domain.usecase

import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

class GetTodoUseCaseTest{

    @Mock
    lateinit var todoRepository: TodoRepository

    @Mock
    lateinit var todoTableMapper: TodoTableMapper

    @Mock
    lateinit var getTodoUseCase: GetTodoUseCase

    @BeforeEach
    fun setUp() {
        getTodoUseCase = GetTodoUseCase(todoRepository, todoTableMapper)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun insertData() = runBlocking {
        assertNotNull(getTodoUseCase.getTodoDetails())
    }

}