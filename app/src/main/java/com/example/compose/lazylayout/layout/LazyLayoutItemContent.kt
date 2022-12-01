package com.example.compose.lazylayout.layout

import androidx.compose.runtime.Composable
import com.example.compose.lazylayout.ListItem

typealias ComposableItemContent = @Composable (ListItem) -> Unit

data class LazyLayoutItemContent(
    val item: ListItem,
    val itemContent: ComposableItemContent
)
