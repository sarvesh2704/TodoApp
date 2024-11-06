package com.example.todoapplication.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is for addTodoScreen
 **/
@HiltViewModel
class AddTodoViewModel
@Inject
constructor(
    private val todoRepository: TodoRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddTodoState.NONE)
    val state = _state

    /**
     * This function is used to insert data in database and add a delay of 3 seconds before updating
     * the state if text entered is error throw and exception else return success
     **/
    fun insertTodoDetail(todoContent: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (todoContent.equals("error", true)) {
                _state.value = AddTodoState.FAILURE
            } else {
                _state.value = AddTodoState.LOADING
                todoRepository.insertTodoDetail(todoContent).collectLatest {
                    delay(3000L)
                    _state.value = AddTodoState.SUCCESS
                }
            }
        }
    }

    enum class AddTodoState {
        NONE,
        SUCCESS,
        LOADING,
        FAILURE
    }

}
