package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(primaryKeys = ["emp_no","dept_no"],
        foreignKeys = [
            ForeignKey(
                entity = Employee::class,
                parentColumns = ["emp_no"],
                childColumns = ["emp_no"],
                onDelete = CASCADE),
            ForeignKey(
                entity = Departments::class,
                parentColumns = ["dept_no"],
                childColumns = ["dept_no"],
                onDelete = CASCADE)

        ])
data class DepartEmployee(
    val dept_no: String,
    val emp_no: Int,
    val from_date: String,
    val to_date: String
)