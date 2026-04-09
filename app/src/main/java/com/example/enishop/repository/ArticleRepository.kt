package com.example.enishop.repository

import android.R.attr.category
import android.content.Context
import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDao
import com.example.enishop.dao.DaoType
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.dao.network.ArticleDaoNetworkImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    val articleDaoMemory: ArticleDaoMemoryImpl,
    val articleDaoDB : ArticleDao,
    val articleDaoNetwork : ArticleDaoNetworkImpl,

    ) {

    fun getArticle(id:Long): Article{
        return articleDaoDB.findById(id)
    }
    suspend fun getArticles(): List<Article> {
        if(articleDaoDB.getAll().isEmpty()){
            articleDaoNetwork.getProducts().forEach {articleNetwork->
                articleNetwork.let { articleNetwork->
                    val articleToInsert = Article(
                        id = articleNetwork.id.toLong(),
                        name = articleNetwork.title,
                        description = articleNetwork.description,
                        price = articleNetwork.price,
                        urlImage = articleNetwork.images.firstOrNull() ?: "",
                        category = articleNetwork.category.name,
                        date = articleNetwork.creationAt
                    )
                    articleDaoDB.insert(articleToInsert)
                }

            }
        }
        return  articleDaoDB.getAll()
    }
    fun addArticle(article: Article): Long{
        return articleDaoMemory.insert(article)
    }
}