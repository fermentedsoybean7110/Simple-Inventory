package com.example.simpleinventory.ui.items

import com.example.simpleinventory.data.model.Item

data class ItemsUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
