package com.example.mod6datastore.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mod6datastore.vm.SettingsViewModel
import com.example.mod6datastore.vm.SettingsViewModel.Companion.Factory

@Composable
fun SettingsPage(modifier: Modifier = Modifier,
                 vm : SettingsViewModel= viewModel(factory = Factory)) {


    //Stockage viewmodel
    val address2State by vm.address2.collectAsState()
    val addressState by vm.address.collectAsState()
    val companyCodeState by vm.companyCode.collectAsState()
    val fidelityState by vm.fidelity.collectAsState()


    //Stockage local du formulaire
    var address2 by remember { mutableStateOf(address2State) }
    var address by remember { mutableStateOf(addressState) }
    var companyCode by rememberSaveable { mutableStateOf(companyCodeState) }
    var fidelity by rememberSaveable { mutableStateOf(fidelityState) }
    //Initialiser les variables locales
    address2 = address2State
    address = addressState
    companyCode = companyCodeState
    fidelity = fidelityState

    Scaffold {innerPadding->
        Column(
            Modifier.fillMaxWidth().padding(innerPadding).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = address,
                label = {Text("Addresse")},
                onValueChange = { address = it},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {if(addressState != address) IconButton({
                    vm.setAddress(address)
                }){
                    Icon(Icons.Default.Check, contentDescription = "Done")
                } }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = address2,
                label = {Text("Addresse2")},
                onValueChange = { address2 = it},
                modifier = Modifier.fillMaxWidth(),

                trailingIcon = { if(address2 != address2State) IconButton({
                    vm.setAddress2(address2)
                }){
                    Icon(Icons.Default.Check, contentDescription = "Done")
                } }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = companyCode,
                label = {Text("Company Code")},
                onValueChange = { companyCode = it},
                modifier = Modifier.fillMaxWidth(),

                trailingIcon = {  if(companyCode != companyCodeState)IconButton({
                    vm.setCompanyCode(companyCode)
                }){
                    Icon(Icons.Default.Check, contentDescription = "Done")
                } }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = fidelity,
                label = {Text("Code fidélité")},
                onValueChange = { fidelity = it},
                modifier = Modifier.fillMaxWidth(),

                trailingIcon = {if(fidelity != fidelityState) IconButton({
                    vm.setFidelity(fidelity)
                }){
                    Icon(Icons.Default.Check, contentDescription = "Done")
                } }
            )
        }
    }
}