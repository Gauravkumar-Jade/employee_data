package com.gaurav.myemployees.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaurav.myemployees.model.*

@Database(entities = [Employee::class, Salaries::class, Title::class,Departments::class,DepartManager::class,DepartEmployee::class], version = 1, exportSchema = false)
abstract class MainDatabase:RoomDatabase() {

    abstract fun getMainDao():MainDao
}