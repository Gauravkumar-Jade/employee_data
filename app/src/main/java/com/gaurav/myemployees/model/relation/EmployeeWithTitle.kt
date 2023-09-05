package com.gaurav.myemployees.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaurav.myemployees.model.Employee
import com.gaurav.myemployees.model.Title

data class EmployeeWithTitle(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "emp_no",
        entityColumn = "emp_no")

    val title: List<Title>
)
