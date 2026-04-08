package com.example.enishop.di

import android.R.attr.type
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room
import com.example.enishop.dao.ArticleDao
import com.example.enishop.dao.DaoType
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.dao.memory.ArticleDaoMemoryImpl
import com.example.enishop.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//C'est ici que l'on va dire à Hilt Comment injecter
@Module
@InstallIn(SingletonComponent::class)
class EniShopModule {
    //On va dire à Hilt comment injecter un ArticleRepository
    @Provides
    fun provideArticleRepository( articleDaoMemory: ArticleDaoMemoryImpl,
                                  articleDaoDB : ArticleDao) = ArticleRepository(
        articleDaoMemory,articleDaoDB )

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

}


