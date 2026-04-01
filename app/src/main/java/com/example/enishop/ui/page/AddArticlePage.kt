package com.example.enishop.ui.page

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArticlePage(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold( snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {innerPadding->
        Column(Modifier.padding(innerPadding).padding(8.dp)) {
            var expanded by remember { mutableStateOf(false) }
            var category by remember { mutableStateOf("") }
            val options = listOf("PC", "TV", "Téléphone", "Vêtements 4", "Accessoires")
            var titre by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var prix by remember { mutableStateOf("") }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.menuAnchor().fillMaxWidth(), // this modifier brings correctness
                    value = category,
                    readOnly = true,
                    onValueChange = { category = it },
                    label = { Text(text = "Select") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption) },
                            onClick = {
                                category = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            OutlinedTextField(
                value = titre,
                onValueChange = { titre = it },
                label = { Text("Titre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = prix,
                onValueChange = { prix = it },
                label = { Text("Prix") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.weight(1f))
            Button({
                ArticleRepository.addArticle(
                    Article(
                        id = 0,
                        name = titre,
                        description = description,
                        price = prix.toDouble(),
                        category = category,
                        date = "Aujourd'hui",
                        urlImage = ""
                    )
                )
                scope.launch {
                    snackbarHostState.showSnackbar("Article ajouté")
                }
            },Modifier.fillMaxWidth()) {
                Text("Ajouter article")
            }
        }
    }
}