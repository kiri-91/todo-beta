package com.example.mytodobeta.repository.todo

import android.content.Context
import androidx.room.Room
import com.example.mytodobeta.model.todo.ToDoDAO
import com.example.mytodobeta.model.todo.ToDoDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// ToDoDAOはRoom経由で作るインスタンスなのでHiltは作り方を知らない
// Hiltモジュールを作る
@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {
    @Singleton
    @Provides
    // Contextを引く数として受け取りたい場合、
    // @ApplicationContextアノテーションを付けるとHiltはアプリケーションコンテキストを渡してくれる
    fun provideToDoDatabase(
        @ApplicationContext context: Context
    ): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo.db"
        ).build()
    }

    @Singleton
    @Provides
    // Daggerの寒冷でprovideで始まるメソッド名
    // ToDoDAOを返すメソッド、引数にはToDoDAOを作るのに必要なもの。
    fun provideToDoDAO(db: ToDoDatabase): ToDoDAO {
        return db.todoDAO()
    }
}

@Module
@InstallIn(SingletonComponent::class)
// HiltはToDoRepositoryなインスタンスを要求されたら、ToDoRepositoryImplを渡せば良いと判断してくれるようになる。
abstract class ToDoRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindToDoRepository(
        impl: ToDoRepositoryImpl
    ) : ToDoRepository
}