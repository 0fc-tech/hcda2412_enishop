package com.example.enishop.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.enishop.R
import com.example.enishop.ui.theme.Typography
import com.example.enishop.ui.vm.ListArticleVM

@Composable
fun ListArticlesPage(modifier: Modifier = Modifier,
                     onClickAdd : ()-> Unit,
                     onClickDetail : (id: Long)-> Unit,
                     vm : ListArticleVM= viewModel(factory = ListArticleVM.Factory)) {
    val listArticleState by vm.listArticlesStateFlow.collectAsState()
    val listFilters by vm.listFiltersStateFlow.collectAsState()
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = onClickAdd
        ){ Icon(Icons.Default.Add, contentDescription ="Add") }
    }){
        innerPadding ->
        Column( modifier.fillMaxSize().padding(innerPadding)) {
            LazyRow {
                items(listFilters){filter->
                    FilterChip(filter.selected, onClick = {
                        vm.filter(filter.label)
                    },
                    label = {
                        Text(filter.label)
                    })
                }
            }
            LazyVerticalGrid(GridCells.FixedSize(200.dp),
                modifier.fillMaxSize()){
                items(listArticleState){article->
                    Card(Modifier
                        .height(220.dp)
                        .padding(8.dp)
                        .clickable(onClick = {
                            onClickDetail(article.id)
                        })){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp).fillMaxSize()) {
                            AsyncImage(
                                article.urlImage,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(120.dp)
                            )
                            Text(article.name, style = Typography.titleMedium,
                                textAlign = TextAlign.Justify,
                                maxLines = 2
                            )
                            Text("${article.price}€")
                        }

                    }
                }

            }


        }

    }
}