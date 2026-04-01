package com.example.enishop.dao

import com.example.enishop.dao.memory.ArticleDaoMemoryImpl

object DaoFactory {
    fun createArticleDao(type: DaoType): ArticleDao{
        return when(type){
            DaoType.MEMORY-> ArticleDaoMemoryImpl()
            DaoType.NETWORK-> ArticleDaoMemoryImpl()
        }
    }

}