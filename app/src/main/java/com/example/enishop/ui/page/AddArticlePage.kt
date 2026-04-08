package com.example.enishop.ui.page

import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.enishop.bo.Article
import com.example.enishop.repository.ArticleRepository
import com.example.enishop.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArticlePage(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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

            Text("Nouvel article", style = Typography.headlineMedium)
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = titre,
                onValueChange = { titre = it },
                label = { Text("Titre") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    showKeyboardOnFocus = true
                ),
                modifier = Modifier.fillMaxWidth().focusable().focusTarget()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    showKeyboardOnFocus = true
                ),
                modifier = Modifier.fillMaxWidth().focusable().focusTarget()
            )
            OutlinedTextField(
                value = prix,
                onValueChange = {
                    prix = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,

                    showKeyboardOnFocus = true
                ),
                label = { Text("Prix") },
                modifier = Modifier.fillMaxWidth().focusable().focusTarget()
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.menuAnchor().fillMaxWidth().focusable().focusTarget(), // this modifier brings correctness
                    value = category,
                    readOnly = true,
                    onValueChange = { category = it },
                    label = { Text(text = "Catégorie") },
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
            Spacer(Modifier.weight(1f))
            Button({
                ArticleRepository(context).addArticle(
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