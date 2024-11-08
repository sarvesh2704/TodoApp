package com.example.todoapplication.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ActivityScenario.launch
import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import com.example.domain.model.TodoModel
import com.example.domain.usecase.GetTodoUseCase
import com.example.domain.usecase.InsertTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

class TodoListViewModelTest {

    @Mock
    lateinit var todoRepository: TodoRepository

    @Mock
    lateinit var todoTableMapper: TodoTableMapper

    @Mock
    lateinit var getTodoUseCase: GetTodoUseCase

    @Mock
    lateinit var todoListViewModel: TodoListViewModel

    @Mock
    lateinit var stateHandle: SavedStateHandle

    @BeforeEach
    fun setUp() {
        getTodoUseCase = GetTodoUseCase(todoRepository, todoTableMapper)
        todoListViewModel = TodoListViewModel(getTodoUseCase, stateHandle)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getAllTodoDetails() = runBlocking {
        val todoModel = TodoModel(0, "a")
        val dataList = mutableListOf<TodoModel>()
        val filteredDataList = mutableListOf<TodoModel>()
        val job = launch(Dispatchers.IO) {
            getTodoUseCase.getTodoDetails().collectLatest {
                dataList.clear()
                filteredDataList.clear()
                dataList.addAll(listOf(todoModel))
                filteredDataList.addAll(listOf(todoModel))
            }
        }
        assertNotNull(dataList)
        assertNotNull(filteredDataList)
        job.cancel()
    }

    @Test
    fun filterDataEmptyString() {
        val text = ""
        val dataList = listOf("a", "b", "c")
        val filteredList = mutableListOf<String>()
        doReturn(filteredList.addAll(dataList)).`when`(todoListViewModel.filterData(text))
        assertEquals(dataList, filteredList)
    }

    @Test
    fun filterDataNotEmptyString() {
        val text = "a"
        val dataList = listOf(TodoModel(0, "a"), TodoModel(1, "b"), TodoModel(2, "c"))
        val filteredList = mutableListOf<TodoModel>()
        doReturn(filteredList.addAll(dataList.filter { it.todoContent.contains(text, true) })).`when`(todoListViewModel.filterData(text))
        assertNotNull(filteredList)
    }
}
