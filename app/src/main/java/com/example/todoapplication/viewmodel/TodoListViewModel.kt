package com.example.todoapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.model.TodoModel
import com.example.todoapplication.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is for todoScreen or main screen
 **/
@HiltViewModel
class TodoListViewModel
@Inject
constructor(
    private val todoRepository: TodoRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    var dataList = mutableStateListOf<TodoModel>()
    var filteredDataList = mutableStateListOf<TodoModel>()

    /**
     * This function is used to fetch the data from database and assign it to 2 list one list will
     * hold the entire data and other will hold filtered data
     **/
    fun getAllTodoDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.getAllTodoDetails().collectLatest {
                dataList.clear()
                filteredDataList.clear()
                dataList.addAll(it)
                filteredDataList.addAll(it)
            }
        }
    }

    /**
     * This function is used to filter the data based on text entered by user and based on use case
     * data is filter and updated
     **/
    fun filterData(text: String) {
        filteredDataList.clear()
        if (text.isNotEmpty()) {
            filteredDataList.addAll(dataList.filter { it.todoContent.contains(text, true) })
        } else {
            filteredDataList.addAll(dataList)
        }
    }
}
