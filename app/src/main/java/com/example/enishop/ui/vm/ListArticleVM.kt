package com.example.enishop.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.enishop.bo.Article
import com.example.enishop.dao.db.AppDatabase
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.Filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ListArticleVM(val repo : ArticleRepository) : ViewModel() {
    private val _listArticlesStateFlow = MutableStateFlow<List<Article>>(listOf());
    val listArticlesStateFlow = _listArticlesStateFlow.asStateFlow()
    private val _listCategory = listOf("Clothes","Electronics","Furniture","Shoes","Miscellaneous")
    private val listFilters = _listCategory.map {category-> Filter(category , false) }

    private val _listFiltersStateFlow = MutableStateFlow(listFilters);
    val listFiltersStateFlow = _listFiltersStateFlow.asStateFlow()
    init{
        viewModelScope.launch(Dispatchers.IO) {
            _listArticlesStateFlow.value = repo.getArticles()
        }
    }

    fun filter(category: String){
        //Liste Filtre => MaCategory : false
        _listFiltersStateFlow.value = _listFiltersStateFlow.value.map {
            if (it.label == category) {
                it.copy(selected = !it.selected)
            } else {
                it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            //Est-ce qu'il y a un filtre a true, s'il y en a pas, je récupère tous les articles
            if(_listFiltersStateFlow.value.firstOrNull{ it.selected } == null){
                _listArticlesStateFlow.value = repo.getArticles()
            }else{
                //Filtrage de mes articles
                _listArticlesStateFlow.value = repo.getArticles().filter { article->
                    //Vérifier que la catégorie soit présente dans _listFiltersStateFlow ET qu'elle soit "true"
                    _listFiltersStateFlow.value.find{ filter -> filter.label == article.category && filter.selected } != null
                }
            }
        }

    }

    companion object{
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ListArticleVM(ArticleRepository(application.applicationContext)) as T
            }
        }
    }


}