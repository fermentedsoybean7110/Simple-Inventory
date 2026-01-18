package com.example.simpleinventory.di

import android.content.Context
import androidx.room.Room
import com.example.simpleinventory.data.db.AppDatabase
import com.example.simpleinventory.data.db.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "simple_inventory_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()
}
