package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["emp_no","from_date"],
    foreignKeys = [
        ForeignKey(
            entity = Employee::class,
            parentColumns = ["emp_no"],
            childColumns = ["emp_no"],
            onDelete = CASCADE)] )
data class Salaries(
    val emp_no: Int,
    val from_date: String,
    val salary: Int,
    val to_date: String
)