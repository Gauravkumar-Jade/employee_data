package com.gaurav.myemployees.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gaurav.myemployees.model.DepartManager
import com.gaurav.myemployees.model.Departments

data class DepartmentWithDepartManager(
    @Embedded val departments: Departments,

    @Relation(
        parentColumn = "dept_no",
        entityColumn = "dept_no")

    val departManager: List<DepartManager>
)