package com.example.domain.usecase

import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

class InsertTodoUseCaseTest {

    @Mock
    lateinit var todoRepository: TodoRepository

    @Mock
    lateinit var todoTableMapper: TodoTableMapper

    @Mock
    lateinit var insertTodoUseCase: InsertTodoUseCase

    @BeforeEach
    fun setUp() {
        insertTodoUseCase = InsertTodoUseCase(todoRepository, todoTableMapper)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun insertData() = runBlocking {
        Assertions.assertNotNull(insertTodoUseCase.insertData("abc"))
    }

}