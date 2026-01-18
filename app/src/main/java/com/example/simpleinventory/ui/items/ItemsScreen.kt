package com.example.simpleinventory.ui.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simpleinventory.data.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(viewModel: ItemsViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editingItem by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { editingItem = null; showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(state.items, key = { it.id }) { item ->
                    ItemRow(item = item, onClick = { clicked -> editingItem = clicked; showDialog = true })
                }
            }
        }
    }

    if (showDialog) {
        ItemEditDialog(initial = editingItem, onConfirm = { name, qty ->
            if (editingItem == null) {
                viewModel.addItem(name, qty)
            } else {
                viewModel.updateItem(editingItem!!.copy(name = name, quantity = qty))
            }
            showDialog = false
        }, onDismiss = { showDialog = false })
    }
}
