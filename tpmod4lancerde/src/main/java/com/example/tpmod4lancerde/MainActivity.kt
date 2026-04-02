package com.example.tpmod4lancerde

import android.R.attr.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpmod4lancerde.ui.theme.ENIShopTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ENIShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LancerDePage(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
///Afficher une page qui affiche un bouton "Lancer dé" et la valeur du dé
@Composable
fun LancerDePage(modifier: Modifier = Modifier,
                 vm: LancerDeVM = viewModel()) {
    val deState by vm.valeurDeState.collectAsState()
    Column(modifier.padding(8.dp,).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(
                when(deState){
                    1 -> R.drawable.d1
                    2 -> R.drawable.d2
                    3 -> R.drawable.d3
                    4 -> R.drawable.d4
                    5 -> R.drawable.d5
                    6 -> R.drawable.d6
                    else -> R.drawable.d1
                },
            ),
        modifier=Modifier.size(64.dp),
        contentDescription="")
        Button({vm.lancerDe()}) {
            Text("Relancer")
        }
    }
}