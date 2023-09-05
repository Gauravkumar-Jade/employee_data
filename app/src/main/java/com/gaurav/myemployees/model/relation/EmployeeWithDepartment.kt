package com.gaurav.myemployees.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaurav.myemployees.model.DepartEmployee
import com.gaurav.myemployees.model.Employee

data class EmployeeWithDepartment(
    @Embedded val employee: Employee,

    @Relation(
        parentColumn = "emp_no",
        entityColumn = "emp_no")

    val departEmployee: List<DepartEmployee>
)
