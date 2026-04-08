package com.example.enishop.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishop.bo.Article
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArticleVM @Inject constructor(val repo : ArticleRepository) : ViewModel() {

    fun addArticle(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addArticle(article)
        }

    }
}