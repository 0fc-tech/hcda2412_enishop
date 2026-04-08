package com.example.enishop.dao

import android.content.Context
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl

object DaoFactory {
    fun createArticleDao(type: DaoType,context: Context): ArticleDao{
        return when(type){
            DaoType.MEMORY-> ArticleDaoMemoryImpl()
            DaoType.NETWORK-> ArticleDaoMemoryImpl()
            DaoType.DB-> AppDatabase.getInstance(context).articleDao()
        }
    }

}