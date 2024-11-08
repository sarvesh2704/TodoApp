package com.example.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.dao.TodoDao
import com.example.data.database.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * This object is used to create a instance of room database as we can't inject its constructor so we
 * created a module class to bind the object and access it
 **/
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) : Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(database: Database) : TodoDao {
        return database.todoDao()
    }

}