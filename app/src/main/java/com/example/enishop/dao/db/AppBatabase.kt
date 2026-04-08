package com.example.enishop.dao.db

import android.content.Context
import androidx.compose.ui.input.key.Key.Companion.Music
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, "EniShop.db"
                ).fallbackToDestructiveMigration(false).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}