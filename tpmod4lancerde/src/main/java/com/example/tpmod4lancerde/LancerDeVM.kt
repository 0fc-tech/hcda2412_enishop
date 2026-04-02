package com.example.tpmod4lancerde

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LancerDeVM : ViewModel() {
    private val _valeurDeState = MutableStateFlow(0)
    val valeurDeState = _valeurDeState.asStateFlow()

    fun lancerDe(){
        _valeurDeState.value = (0..6).random()
    }
}