package com.example.domain.mapper

import com.example.data.database.tables.TodoTable
import com.example.domain.model.TodoModel
import com.example.domain.util.EntityMapper
import javax.inject.Inject

class TodoTableMapper
@Inject
constructor() : EntityMapper<TodoTable, TodoModel> {
    override fun mapFromEntity(entity: TodoTable): TodoModel {
        return TodoModel(
            id = entity.id,
            todoContent = entity.todoContent
        )
    }

    override fun mapToEntity(domainModel: TodoModel): TodoTable {
        return TodoTable(
            id = 0,
            todoContent = domainModel.todoContent
        )
    }

    fun mapFromEntityList(entities : List<TodoTable>) :List<TodoModel>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities : List<TodoModel>) :List<TodoTable>{
        return entities.map { mapToEntity(it) }
    }

}