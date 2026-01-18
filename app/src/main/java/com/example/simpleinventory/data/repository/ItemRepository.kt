package com.example.simpleinventory.data.repository

import com.example.simpleinventory.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun observeItems(): Flow<List<Item>>
    suspend fun addItem(item: Item)
    suspend fun updateItem(item: Item)
    suspend fun deleteItem(item: Item)
}
