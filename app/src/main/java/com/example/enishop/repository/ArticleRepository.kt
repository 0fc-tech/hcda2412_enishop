package com.example.enishop.repository

import android.content.Context
import com.example.enishop.bo.Article
import com.example.enishop.dao.DaoFactory
import com.example.enishop.dao.DaoType
import com.example.enishop.dao.db.AppDatabase

class ArticleRepository(contextApp : Context) {
    val articleDaoMemory = DaoFactory.createArticleDao(DaoType.MEMORY,contextApp)
    val articleDaoDB = DaoFactory.createArticleDao(DaoType.DB,contextApp)


    fun getArticle(id:Long): Article{
        return articleDaoMemory.findById(id)
    }
    fun getArticles(): List<Article> {
        if(articleDaoDB.getAll().isEmpty()){
            articleDaoMemory.getAll().forEach { articleMemory->
                articleDaoDB.insert(articleMemory)
            }
        }
        return  articleDaoDB.getAll()
    }
    fun addArticle(article: Article): Long{
        return articleDaoMemory.insert(article)
    }
}