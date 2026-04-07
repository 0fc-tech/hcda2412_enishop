package com.example.enishop.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State.Empty.painter
import coil3.compose.rememberAsyncImagePainter
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.theme.Typography

@Composable
fun DetailArticlePage(modifier: Modifier = Modifier,
                      idArticle : Long = 1) {
    val article = ArticleRepository.getArticle(idArticle)
    Scaffold {innerPadding->
        Column(Modifier.padding(innerPadding)) {
            //Faire le contenu
            AsyncImage(article.urlImage,
                contentDescription = article.urlImage,
                modifier=Modifier.height(200.dp)
            )
            Text(article.name,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            Row(modifier = Modifier.padding(8.dp)){
                Text(article.price.toString())
                Spacer(Modifier.weight(1F))
                Text(article.category)
            }
            Text(article.description, modifier = Modifier.padding(8.dp))
            Spacer(Modifier.weight(1F))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("Ajouter au panier")
            }

        }
    }
}