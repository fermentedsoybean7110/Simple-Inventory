package com.example.simpleinventory.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleinventory.data.model.Item
import com.example.simpleinventory.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ItemsUiState())
    val uiState: StateFlow<ItemsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeItems().collect { list ->
                _uiState.update { it.copy(items = list) }
            }
        }
    }

    fun addItem(name: String, quantity: Int) {
        if (name.isBlank()) {
            _uiState.update { it.copy(error = "Name must not be blank") }
            return
        }
        viewModelScope.launch {
            repository.addItem(Item(name = name.trim(), quantity = quantity))
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            repository.updateItem(item.copy(updatedAt = System.currentTimeMillis()))
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}
