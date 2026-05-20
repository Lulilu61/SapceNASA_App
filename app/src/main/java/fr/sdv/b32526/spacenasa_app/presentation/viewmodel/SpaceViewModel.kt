package fr.sdv.b32526.spacenasa_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.sdv.b32526.spacenasa_app.data.AstrosResponse
import fr.sdv.b32526.spacenasa_app.data.SpaceApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AstrosResponse?>(null)

    val uiState: StateFlow<AstrosResponse?> = _uiState.asStateFlow()

    init {
        loadAstronauts()
    }
    fun loadAstronauts() {
        viewModelScope.launch {
            try {
                val response = SpaceApiClient.fetchAstronauts()
                _uiState.value = response
            } catch (e: Exception) {
                println("ERREUR NASA API : ${e.message}")
                e.printStackTrace()

                _uiState.value = AstrosResponse(
                    status = "error",
                    totalAstronauts = 0,
                    astronautsList = emptyList()
                )
            }
        }
    }
    /*fun loadAstronauts() {
        viewModelScope.launch {
            try {
                val response = SpaceApiClient.fetchAstronauts()
                _uiState.value = response
            } catch (e: Exception) {
                _uiState.value = null
            }
        }
    }*/
}