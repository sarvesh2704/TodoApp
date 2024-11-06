package com.example.todoapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.todoapplication.R
import com.example.todoapplication.navigation.Route
import com.example.todoapplication.util.Dimens
import com.example.todoapplication.util.Loader
import com.example.todoapplication.viewmodel.AddTodoViewModel
import com.example.todoapplication.viewmodel.CommonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    navController: NavController,
    addTodoViewModel: AddTodoViewModel,
    commonViewModel: CommonViewModel
) {

    var inputText by remember { mutableStateOf("") }

    // collect or observe state and take action based on different states
    when (addTodoViewModel.state.collectAsState().value) {
        AddTodoViewModel.AddTodoState.SUCCESS -> navigateToTodoListScreen(navController)
        AddTodoViewModel.AddTodoState.LOADING -> Loader()
        AddTodoViewModel.AddTodoState.FAILURE -> {
            commonViewModel.isError.value = true
            navigateToTodoListScreen(navController)
        }
        else -> {}
    }

    Column(
        modifier = Modifier.padding(top = Dimens.MARGIN_TWENTY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dimens.MARGIN_TWENTY),
            singleLine = true,
            value = inputText,
            onValueChange = { inputText = it }
        )

        Button(modifier = Modifier
            .wrapContentSize()
            .padding(top = Dimens.MARGIN_TWENTY),
            enabled = inputText.isNotBlank(),
            onClick = {
                addTodoViewModel.insertTodoDetail(inputText)
            }) {
            Text(text = stringResource(id = R.string.add_todo))
        }

    }

}

/**
 * function is used to take user back todoListScreen and popUpTo is use to remove the same screen
 * from stack and when back press it should close the app
 **/
fun navigateToTodoListScreen(navController: NavController){
    navController.navigate(Route.TODO_LIST_SCREEN.name){
        popUpTo(Route.TODO_LIST_SCREEN.name){
            inclusive = true
        }
    }
}
