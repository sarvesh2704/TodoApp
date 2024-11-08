package com.example.todoapplication.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import com.example.domain.usecase.InsertTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.delay
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

class AddTodoViewModelTest {

    @Mock
    lateinit var todoRepository: TodoRepository

    @Mock
    lateinit var todoTableMapper: TodoTableMapper

    @Mock
    lateinit var insertTodoUseCase: InsertTodoUseCase

    @Mock
    lateinit var addTodoViewModel: AddTodoViewModel

    @Mock
    lateinit var stateHandle: SavedStateHandle

    @BeforeEach
    fun setUp() {
        insertTodoUseCase = InsertTodoUseCase(todoRepository, todoTableMapper)
        addTodoViewModel = AddTodoViewModel(insertTodoUseCase, stateHandle)
    }

    @AfterEach
    fun tearDown(){

    }

    @Test
    fun insertTodoDetailSuccess() = runBlocking {
        val _stateFlow = MutableStateFlow(AddTodoViewModel.AddTodoState.NONE)
        val stateFlow = _stateFlow.asStateFlow()
        val text = "error1"

        val job = launch(Dispatchers.IO) {
            insertTodoUseCase.insertData(text).collectLatest {
                _stateFlow.value = AddTodoViewModel.AddTodoState.SUCCESS
            }
        }
        addTodoViewModel.insertTodoDetail(text)

        Assertions.assertEquals(stateFlow.value, AddTodoViewModel.AddTodoState.SUCCESS)
        job.cancel()
    }

    @Test
    fun insertTodoDetailError() = runBlocking {
        val _stateFlow = MutableStateFlow(AddTodoViewModel.AddTodoState.NONE)
        val stateFlow = _stateFlow.asStateFlow()
        val text = "error"

        addTodoViewModel.insertTodoDetail(text)
        _stateFlow.value = AddTodoViewModel.AddTodoState.FAILURE

        Assertions.assertEquals(stateFlow.value, AddTodoViewModel.AddTodoState.FAILURE)
    }

}

