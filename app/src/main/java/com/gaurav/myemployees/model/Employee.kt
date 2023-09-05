package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["emp_no"])
data class Employee(
    val emp_no: Int,
    val birth_date: String,
    val first_name: String,
    val gender: String,
    val hire_date: String,
    val last_name: String
)