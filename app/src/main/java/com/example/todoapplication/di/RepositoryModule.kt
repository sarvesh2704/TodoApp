package com.example.todoapplication.di

import com.example.todoapplication.database.dao.TodoDao
import com.example.todoapplication.database.mapper.TodoTableMapper
import com.example.todoapplication.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * This object is used to create a instance of repository as we can't inject its constructor so we
 * created a module class to bind the object and access it
 **/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTodoRepository(
        todoTableMapper: TodoTableMapper,
        todoDao: TodoDao
    ): TodoRepository {
        return TodoRepository(todoTableMapper, todoDao)
    }

}