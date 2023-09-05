package com.gaurav.myemployees.database

import androidx.room.*
import com.gaurav.myemployees.model.*
import com.gaurav.myemployees.model.relation.*

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSalaries(salaries: Salaries)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTitle(title: Title)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDepartment(departments: Departments)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmplDepartment(departEmployee: DepartEmployee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertManager(manager: DepartManager)

    @Query("SELECT * FROM Employee")
    fun getAllEmployee():List<Employee>

    @Query("SELECT * FROM Departments")
    fun getDepartment():List<Departments>

    @Transaction
    @Query("SELECT * FROM Employee WHERE emp_no = :emp_no")
    fun getEmployeeWithSalary(emp_no:String):EmployeeWithSalaries

    @Transaction
    @Query("SELECT * FROM Employee WHERE emp_no = :emp_no")
    fun getEmployeeWithTitle(emp_no:String):EmployeeWithTitle

    @Transaction
    @Query("SELECT * FROM Departments WHERE dept_no = :dept_no")
    fun getDepartmentWithManager(dept_no:String):DepartmentWithDepartManager


    @Transaction
    @Query("SELECT * FROM Employee WHERE emp_no = :emp_no")
    fun getEmployeeWithDepartment(emp_no:String):EmployeeWithDepartment


    @Transaction
    @Query("SELECT * FROM Departments WHERE dept_no = :dept_no")
    fun getDepartmentWithDepartEmployee(dept_no: String):DepartmentWithDepartEmployee
}