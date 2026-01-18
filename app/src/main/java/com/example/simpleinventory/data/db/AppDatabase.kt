package com.example.simpleinventory.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleinventory.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
