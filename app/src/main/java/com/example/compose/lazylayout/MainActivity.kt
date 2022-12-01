package com.example.compose.lazylayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.lazylayout.ui.theme.LazyLayoutBlogTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyLayoutBlogTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                CustomLazyLayoutScreen(state = state, actions = viewModel)
            }
        }
    }
}
