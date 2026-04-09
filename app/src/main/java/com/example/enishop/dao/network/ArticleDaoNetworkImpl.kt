package com.example.enishop.dao.network

import com.example.enishop.bo.network.ArticleNetwork
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ArticleDaoNetworkImpl {
    @GET("products")
    suspend fun getProducts(): List<ArticleNetwork>

}
