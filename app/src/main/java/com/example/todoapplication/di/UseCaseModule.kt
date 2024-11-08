package com.example.todoapplication.di

import com.example.data.repository.TodoRepository
import com.example.domain.mapper.TodoTableMapper
import com.example.domain.usecase.GetTodoUseCase
import com.example.domain.usecase.InsertTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This object is used to create a instance of use-case as we can't inject its constructor so we
 * created a module class to bind the object and access it
 **/
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideInsertTodoUseCase(
        todoRepository: TodoRepository,
        todoTableMapper: TodoTableMapper
    ): InsertTodoUseCase {
        return InsertTodoUseCase(todoRepository, todoTableMapper)
    }
    @Singleton
    @Provides
    fun provideGetTodoUseCase(
        todoRepository: TodoRepository,
        todoTableMapper: TodoTableMapper
    ): GetTodoUseCase {
        return GetTodoUseCase(todoRepository, todoTableMapper)
    }

}
