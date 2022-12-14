package com.example.mytodobeta.repository.todo

import com.example.mytodobeta.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

// todoを永続化する機能を提供
interface ToDoRepository {
    fun getAl(): Flow<List<ToDo>>
    suspend fun create(title: String, detail: String)
    suspend fun update(todo: ToDo, title: String, detail: String): ToDo
    suspend fun delete(todo: ToDo)
}