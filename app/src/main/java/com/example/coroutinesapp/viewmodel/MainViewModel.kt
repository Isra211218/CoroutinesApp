package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel

class MainViewModel : ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var counter1 by mutableStateOf(0)
        private set

    var counter2 by mutableStateOf(0)
        private set

    private var counterJob: Job? = null

    fun startCounters() {
        counterJob?.cancel()
        counter1 = 0
        counter2 = 0
        resultState = ""  // Limpia el mensaje de estado al iniciar los contadores

        counterJob = viewModelScope.launch {
            runFirstCounter()
            runSecondCounter()
            resultState = "Contadores finalizados"
        }
    }

    private suspend fun runFirstCounter() {
        for (i in 1..5) {
            delay(1000)
            counter1 = i
        }
    }

    private suspend fun runSecondCounter() {
        for (i in 1..5) {
            delay(1000)
            counter2 = i
        }
    }

    fun cancelCounters() {
        counterJob?.cancel()
        resultState = "Contadores cancelados"
    }
}
