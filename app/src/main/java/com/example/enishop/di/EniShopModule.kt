package com.example.enishop.di

import android.R.attr.type
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room
import com.example.enishop.dao.ArticleDao
import com.example.enishop.dao.DaoType
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.dao.network.ArticleDaoNetworkImpl
import com.example.enishop.repository.ArticleRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

//C'est ici que l'on va dire à Hilt Comment injecter
@Module
@InstallIn(SingletonComponent::class)
class EniShopModule {
    //On va dire à Hilt comment injecter un ArticleRepository
    @Provides
    fun provideArticleRepository( articleDaoMemory: ArticleDaoMemoryImpl,
                                  articleDaoDB : ArticleDao,
                                  articleDaoNetwork : ArticleDaoNetworkImpl,
                                  ) = ArticleRepository(
        articleDaoMemory,articleDaoDB, articleDaoNetwork
    )

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "EniShop.db"
    ).fallbackToDestructiveMigration(false).build()
    //Tout les repository
    //Toutes les DAO
    @Provides
    fun provideArticleDbDao(db: AppDatabase)= db.articleDao()

    @Provides
    fun provideArticleMemoryDao()= ArticleDaoMemoryImpl()

    @Provides
    fun provideArticleApiDao(): ArticleDaoNetworkImpl {
        val BASE_URL = "https://api.escuelajs.co/api/v1/"
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ArticleDaoNetworkImpl::class.java)
    }
}


