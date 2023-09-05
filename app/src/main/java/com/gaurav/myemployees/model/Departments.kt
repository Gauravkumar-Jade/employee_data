package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Departments(
    val dept_name: String,
    @PrimaryKey(autoGenerate = false)
    val dept_no: String
)