package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(primaryKeys = ["dept_no","emp_no"],
        foreignKeys = [
            ForeignKey(
                entity = Departments::class,
                parentColumns = ["dept_no"],
                childColumns = ["dept_no"],
                onDelete = CASCADE)])
data class DepartManager(
    val emp_no: Int,
    val dept_no: String,
    val from_date: String,
    val to_date: String
)