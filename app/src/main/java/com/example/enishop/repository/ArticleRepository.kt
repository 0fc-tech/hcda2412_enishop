package com.example.enishop.repository

import com.example.enishop.bo.Article
import com.example.enishop.dao.DaoFactory
import com.example.enishop.dao.DaoType

object ArticleRepository {
    val articleDaoMemory = DaoFactory.createArticleDao(
        DaoType.MEMORY)
    fun getArticle(id:Long): Article{
        return articleDaoMemory.findById(id)
    }
    fun addArticle(article: Article): Long{
        return articleDaoMemory.insert(article)
    }
}