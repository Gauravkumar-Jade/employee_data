package com.gaurav.myemployees.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaurav.myemployees.model.Departments
import com.gaurav.myemployees.model.DepartEmployee

data class DepartmentWithDepartEmployee(
    @Embedded val departments: Departments,
    @Relation(
        parentColumn = "dept_no",
        entityColumn = "dept_no"
    )

    val departEmployee: List<DepartEmployee>
)
