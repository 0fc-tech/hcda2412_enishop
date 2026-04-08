package com.example.enishop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.enishop.ui.page.AddArticlePage
import com.example.enishop.ui.page.DetailArticlePage
import com.example.enishop.ui.page.ListArticlesPage
import com.example.enishop.ui.theme.ENIShopTheme
import com.example.enishop.ui.vm.AddArticleVM
import com.example.enishop.ui.vm.DetailArticleVM
import com.example.enishop.ui.vm.ListArticleVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ENIShopTheme {
                ContainerNavigation()
                //DetailArticlePage()
                //AddArticlePage()
                //ListArticlesPage()
            }
        }
    }
}

//ICI créer le contenur de navigation
@Composable
fun ContainerNavigation(modifier: Modifier = Modifier) {
    val navHostController: NavHostController = rememberNavController()

    NavHost(
        startDestination = "list",
        navController = navHostController,
    ) {
        composable("list") {
            ListArticlesPage(
                onClickAdd = { navHostController.navigate("add") },
                onClickDetail = { navHostController.navigate(DetailNav(it),) },
                vm = hiltViewModel<ListArticleVM>())
        }
        composable("add") {
            AddArticlePage(vm = hiltViewModel<AddArticleVM>())
        }
        composable<DetailNav>() {backStackEntry ->
            val detailNav = backStackEntry.toRoute<DetailNav>()
            DetailArticlePage(idArticle = detailNav.idArticle,
                vm = hiltViewModel<DetailArticleVM>())
        }
    }

}

@Serializable
data class DetailNav(val idArticle:Long){}

