package com.example.compose.lazylayout

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel(), Actions {

    val state = MutableStateFlow(State())

    private var generateJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    init {
        generateItems()
    }

    private fun generateItems() {
        generateJob = viewModelScope.launch {
            val currentState = state.value
            val items = (0 until currentState.size).map {
                ListItem(
                    Random.nextInt(currentState.maxX),
                    Random.nextInt(currentState.maxY)
                )
            }

            state.value = currentState.copy(
                items = items
            )
        }
    }

    override fun setSize(size: Int) {
        state.value = state.value.copy(size = size)
        generateItems()
    }

    override fun setMaxX(max: Int) {
        state.value = state.value.copy(maxX = max)
        generateItems()
    }

    override fun setMaxY(max: Int) {
        state.value = state.value.copy(maxY = max)
        generateItems()
    }
}

data class State(
    val size: Int = 1_000,
    val maxX: Int = 10_000,
    val maxY: Int = 10_000,
    val items: List<ListItem> = emptyList()
)

@Stable
interface Actions {

    fun setSize(size: Int)

    fun setMaxX(max: Int)

    fun setMaxY(max: Int)
}
