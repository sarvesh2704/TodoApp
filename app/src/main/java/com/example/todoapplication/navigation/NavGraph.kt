package com.example.todoapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapplication.view.AddTodoScreen
import com.example.todoapplication.view.TodoListScreen
import com.example.todoapplication.viewmodel.AddTodoViewModel
import com.example.todoapplication.viewmodel.CommonViewModel
import com.example.todoapplication.viewmodel.TodoListViewModel

/**
 * This class is used to declare all the screens and view model which can be used in that screen
 **/
@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val commonViewModel = hiltViewModel<CommonViewModel>()

    NavHost(navController = navController, startDestination = Route.TODO_LIST_SCREEN.name) {

        composable(route = Route.TODO_LIST_SCREEN.name) {
            val todoListViewModel = hiltViewModel<TodoListViewModel>()
            TodoListScreen(navController = navController, todoListViewModel = todoListViewModel, commonViewModel = commonViewModel)
        }

        composable(route = Route.ADD_TODO_SCREEN.name) {
            val addTodoViewModel = hiltViewModel<AddTodoViewModel>()
            AddTodoScreen(navController = navController, addTodoViewModel = addTodoViewModel, commonViewModel = commonViewModel)
        }

    }

}