package com.example.enishop.dao.memory

import com.example.enishop.bo.Article
import com.example.enishop.dao.ArticleDao

class ArticleDaoMemoryImpl : ArticleDao {
    val articlesInMemory = mutableListOf<Article>(
        Article(
            id = 1,
            name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            price = 109.95,
            urlImage = "https://picsum.photos/id/1/1860/900",
            category = "men's clothing",
            date = "22 juillet 2024",
        ),

        Article(
            id = 2,
            name = "Mens Casual Premium Slim Fit T-Shirts",
            description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
            price = 22.3,
            urlImage = "https://picsum.photos/id/2/1860/900",
            category = "men's clothing",
            date = "25 septembre 2024"
        ),
        Article(
            id = 3,
            name = "Mens Cotton Jacket",
            description = "great outerwear jackets for Spring/Autumn/Winter, suitablefor many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
            price = 55.99,
            urlImage = "https://picsum.photos/id/3/1860/900",
            category = "men's clothing",
            date = "12 sept 2025",
        )
    )


    override fun findById(id: Long): Article {
        val articleNullable = articlesInMemory.find { it.id == id }
        if (articleNullable == null) {
            throw NoSuchElementException()
        }
        return articleNullable
    }

    override fun insert(article: Article): Long {
        articlesInMemory.add(article)
        return -1
    }

}