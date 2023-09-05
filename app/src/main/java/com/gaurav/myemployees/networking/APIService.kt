package com.gaurav.myemployees.networking

import com.gaurav.myemployees.model.*
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("employees.json")
    suspend fun getEmployees():Response<List<Employee>>

    @GET("salaries.json")
    suspend fun getSalaries():Response<List<Salaries>>

    @GET("titles.json")
    suspend fun getTitles():Response<List<Title>>

    @GET("emp_departments.json")
    suspend fun getEmployeeDepartment():Response<List<DepartEmployee>>

    @GET("departments.json")
    suspend fun getDepartments():Response<List<Departments>>

    @GET("department_manager.json")
    suspend fun getDepartManager():Response<List<DepartManager>>
}