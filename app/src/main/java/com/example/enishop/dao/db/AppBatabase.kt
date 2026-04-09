package com.example.enishop.dao.db

import android.content.Context
import androidx.compose.ui.input.key.Key.Companion.Music
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDao

@Database(entities = [Article::class], version =2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
}