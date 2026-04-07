package com.example.mod6datastore.repository

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "user_preferences")
class UserPreferencesRepository(private val context: Context) {
    //Clé Preference Background Color
    private val KEY_ADDRESS = stringPreferencesKey("KEY_ADDRESS")
    private val KEY_ADDRESS2 = stringPreferencesKey("KEY_ADDRESS2")
    private val KEY_COMPANY_CODE = intPreferencesKey("KEY_COMPANY_CODE")
    private val KEY_FIDELITY = intPreferencesKey("KEY_FIDELITY")


    //enregistrer le type de couleur de fond
    suspend fun saveAddress(address: String) {
        context.dataStore.edit { pref ->
            pref[KEY_ADDRESS] = address
        }
    }
    fun getAddress(): Flow<String> =
        context.dataStore.data.map { pref -> pref[KEY_ADDRESS] ?:"" }

    suspend fun saveAddress2(address: String) {
        context.dataStore.edit { pref ->
            pref[KEY_ADDRESS2] = address
        }
    }
    fun getAddress2(): Flow<String> =
        context.dataStore.data.map { pref -> pref[KEY_ADDRESS2] ?:"" }

    suspend fun saveCompanyCode(companyCode: Int) {
        context.dataStore.edit { pref ->
            pref[KEY_COMPANY_CODE] = companyCode
        }
    }
    fun getCompanyCode(): Flow<Int> =
        context.dataStore.data.map { pref -> pref[KEY_COMPANY_CODE] ?:0 }

    suspend fun saveFidelityCode(fidelity: Int) {
        context.dataStore.edit { pref ->
            pref[KEY_FIDELITY] = fidelity
        }
    }
    fun getFidelityCode(): Flow<Int> =
        context.dataStore.data.map { pref -> pref[KEY_FIDELITY] ?:0 }

}