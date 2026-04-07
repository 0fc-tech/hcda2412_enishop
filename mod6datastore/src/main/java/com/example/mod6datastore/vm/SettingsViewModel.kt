package com.example.mod6datastore.vm

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mod6datastore.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val userPreferencesRepository:
                        UserPreferencesRepository
) :
    ViewModel() {

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address
    private val _address2 = MutableStateFlow("")
    val address2: StateFlow<String> = _address2
    private val _companyCode = MutableStateFlow("")
    val companyCode: StateFlow<String> = _companyCode

    private val _fidelity = MutableStateFlow("")
    val fidelity: StateFlow<String> = _fidelity

    init {
        getAddress()
        getAddress2()
        getCompanyCode()
        getFidelity()
    }

    fun getAddress() {
        viewModelScope.launch {
            userPreferencesRepository.getAddress().collect {
                _address.value = it

            }
        }
    }

    fun setAddress(address: String) {
       // _userColor.value = color
        viewModelScope.launch {
            userPreferencesRepository.saveAddress(address)
        }
    }

    fun getAddress2() {
        viewModelScope.launch {
            userPreferencesRepository.getAddress2().collect {
                _address2.value = it

            }
        }
    }

    fun setAddress2(address: String) {
        // _userColor.value = color
        viewModelScope.launch {
            userPreferencesRepository.saveAddress2(address)
        }
    }

    fun getCompanyCode() {
        viewModelScope.launch {
            userPreferencesRepository.getCompanyCode().collect {
                _companyCode.value = it.toString()

            }
        }
    }

    fun setCompanyCode(companyCode: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveCompanyCode(companyCode.toIntOrNull() ?: 0)
        }
    }

    fun getFidelity() {
        viewModelScope.launch {
            userPreferencesRepository.getFidelityCode().collect {
                _fidelity.value = it.toString()

            }
        }
    }

    fun setFidelity(fidelityCode: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveFidelityCode(fidelityCode.toIntOrNull() ?: 0)
        }
    }


    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras) : T{
                val application = checkNotNull(
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                )
                val userPreferencesRepository = UserPreferencesRepository(application)
                return SettingsViewModel(userPreferencesRepository) as T
            }
        }
    }
}