package com.gaurav.myemployees.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["emp_no","title","from_date"],
        foreignKeys = [
            ForeignKey(
                entity = Employee::class,
                parentColumns = ["emp_no"],
                childColumns = ["emp_no"],
                onDelete = CASCADE)])
data class Title(
    val emp_no: Int,
    val from_date: String,
    val title: String,
    val to_date: String
)