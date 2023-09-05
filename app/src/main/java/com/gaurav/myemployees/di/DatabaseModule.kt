package com.gaurav.myemployees.di

import android.content.Context
import androidx.room.Room
import com.gaurav.myemployees.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context):MainDatabase{
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "employees_db")
            .build()
    }
}