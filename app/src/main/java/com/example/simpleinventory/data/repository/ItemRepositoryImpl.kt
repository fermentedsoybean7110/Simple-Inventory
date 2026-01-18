package com.example.simpleinventory.data.repository

import com.example.simpleinventory.data.db.ItemDao
import com.example.simpleinventory.data.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject constructor(
    private val dao: ItemDao
) : ItemRepository {
    override fun observeItems(): Flow<List<Item>> = dao.observeAll()

    override suspend fun addItem(item: Item) {
        withContext(Dispatchers.IO) { dao.insert(item) }
    }

    override suspend fun updateItem(item: Item) {
        withContext(Dispatchers.IO) { dao.update(item) }
    }

    override suspend fun deleteItem(item: Item) {
        withContext(Dispatchers.IO) { dao.delete(item) }
    }
}
