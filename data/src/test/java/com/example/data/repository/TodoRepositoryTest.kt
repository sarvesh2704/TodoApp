package com.example.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.database.dao.TodoDao
import com.example.data.database.db.Database
import com.example.data.database.tables.TodoTable
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

class TodoRepositoryTest {

    @Mock
    lateinit var todoDatabase: Database

    @Mock
    lateinit var todoDao: TodoDao

    @BeforeEach
    fun setUp() {
        todoDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()
        todoDao = todoDatabase.todoDao()
    }

    @AfterEach
    fun tearDown() {
        todoDatabase.close()
    }

    @Test
    fun `insert data in todo details`() = runBlocking {
        val todoTable = TodoTable(0, "abc")
        val result = todoDao.insert(todoTable)
        Assertions.assertNotNull(result)
    }

    @Test
    fun `list of todo details not empty`() = runBlocking {
        val todoTable = TodoTable(0, "abc")
        todoDao.insert(todoTable)
        val list = todoDao.getTodoDetails()
        Assertions.assertNotNull(list)
    }

    @Test
    fun `list of todo details empty`() = runBlocking {
        val list = todoDao.getTodoDetails()
        Assertions.assertNotNull(list)
    }
}
