package com.example.enishop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.enishop.bo.Article
@Dao
interface ArticleDao {
    @Query("SELECT * FROM article WHERE id = :id")
    fun findById(id: Long) : Article
    @Query("SELECT * FROM article")
    fun getAll() : List<Article>
    @Insert
    fun insert(article: Article):Long
}