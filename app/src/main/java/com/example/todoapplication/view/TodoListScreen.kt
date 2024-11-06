package com.example.todoapplication.view

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.todoapplication.R
import com.example.todoapplication.navigation.Route
import com.example.todoapplication.util.Dimens
import com.example.todoapplication.viewmodel.CommonViewModel
import com.example.todoapplication.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavController,
    todoListViewModel: TodoListViewModel,
    commonViewModel: CommonViewModel
) {

    var filterText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        todoListViewModel.getAllTodoDetails()
    }

    if(commonViewModel.isError.value){
        AlertDialog(
            onDismissRequest = {
                commonViewModel.isError.value = false
            },
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = stringResource(id = R.string.failure_error_description)) },
            confirmButton = {
                Button(
                    onClick = {
                        commonViewModel.isError.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = Color.White
                    )
                }
            }
        )
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (textField, noItemTextField, floatingButton, list) = createRefs()

        TextField(
            modifier = Modifier
                .constrainAs(textField) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .padding(all = Dimens.MARGIN_TWENTY),
            singleLine = true,
            value = filterText,
            onValueChange = {
                filterText = it
                todoListViewModel.filterData(filterText)
            }
        )
        // display list when list is not empty
        if (todoListViewModel.dataList.isNotEmpty()) {

            LazyColumn(modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(textField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end, Dimens.MARGIN_TWENTY)
                }
                .padding(bottom = Dimens.MARGIN_FIFTY),
                state = listState) {

                items(todoListViewModel.filteredDataList) { todoDetail ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = Dimens.MARGIN_FIFTY)
                            .wrapContentHeight()
                            .padding(horizontal = Dimens.MARGIN_SIXTEEN),
                        text = todoDetail.todoContent,
                        fontSize = TextUnit(20.0f, TextUnitType.Sp)
                    )
                    Divider()
                }
            }

        } else {
            // Text Field When there no item available in list
            Text(
                modifier = Modifier
                    .constrainAs(noItemTextField) {
                        centerTo(parent)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = stringResource(id = R.string.no_todo_item_description)
            )
        }

        FloatingActionButton(modifier = Modifier
            .constrainAs(floatingButton) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(
                end = Dimens.MARGIN_TWENTY,
                bottom = Dimens.MARGIN_TWENTY
            ),
            onClick = {
                navController.navigate(Route.ADD_TODO_SCREEN.name)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = ""
            )
        }
    }
}
