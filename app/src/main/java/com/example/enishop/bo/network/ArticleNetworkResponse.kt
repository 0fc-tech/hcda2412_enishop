package com.example.enishop.bo.network

data class ArticleNetwork(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Double,
    val description: String,
    val category: CategoryNetwork,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
)
data class CategoryNetwork(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val creationAt: String,
    val updatedAt: String
)