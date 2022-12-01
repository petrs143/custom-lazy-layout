package com.example.compose.lazylayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.lazylayout.layout.CustomLazyLayout
import com.example.compose.lazylayout.layout.LazyLayoutState
import com.example.compose.lazylayout.layout.rememberLazyLayoutState

@Composable
fun CustomLazyLayoutScreen(state: State, actions: Actions) {
    val lazyLayoutState = rememberLazyLayoutState()
    var showSettings by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AnimatedVisibility(showSettings) {
            Settings(state = state, lazyLayoutState = lazyLayoutState, actions = actions)
        }

        Button(
            onClick = { showSettings = !showSettings },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Toggle settings")
        }

        CustomLazyLayout(
            state = lazyLayoutState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.items) { item ->
                Text(
                    text = "X: ${item.x}\nY: ${item.y}",
                    color = Color.White,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
private fun Settings(state: State, lazyLayoutState: LazyLayoutState, actions: Actions) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Offset: X ${lazyLayoutState.offsetState.value.x} Y ${lazyLayoutState.offsetState.value.y}")

        Text("Size: ${state.size}")
        Slider(
            value = state.size.toFloat(),
            onValueChange = { actions.setSize(it.toInt()) },
            valueRange = 1f..20_000f
        )

        Text("Max X: ${state.maxX}")
        Slider(
            value = state.maxX.toFloat(),
            onValueChange = { actions.setMaxX(it.toInt()) },
            valueRange = 20f..20_000f
        )

        Text("Max Y: ${state.maxY}")
        Slider(
            value = state.maxY.toFloat(),
            onValueChange = { actions.setMaxY(it.toInt()) },
            valueRange = 20f..20_000f
        )
    }
}
