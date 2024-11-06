package com.example.todoapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommonViewModel
@Inject
constructor(
    private val stateHandle: SavedStateHandle
): ViewModel() {

    var isError = mutableStateOf(false)

}