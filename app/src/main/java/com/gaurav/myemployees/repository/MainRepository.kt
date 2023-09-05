package com.gaurav.myemployees.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gaurav.myemployees.model.*
import com.gaurav.myemployees.networking.APIService
import com.gaurav.myemployees.networking.NetworkResults
import com.gaurav.myemployees.networking.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: APIService ) {

    private var _employeeList = MutableLiveData<NetworkResults<List<Employee>>>()
    val employeeList: LiveData<NetworkResults<List<Employee>>> get() = _employeeList

    private var _salaryList = MutableLiveData<NetworkResults<List<Salaries>>>()
    val salaryList: LiveData<NetworkResults<List<Salaries>>> get() = _salaryList

    private var _titleList = MutableLiveData<NetworkResults<List<Title>>>()
    val titleList: LiveData<NetworkResults<List<Title>>> get() = _titleList

    private var _departList = MutableLiveData<NetworkResults<List<Departments>>>()
    val departList: LiveData<NetworkResults<List<Departments>>> get() = _departList

    private var _managerList = MutableLiveData<NetworkResults<List<DepartManager>>>()
    val managerList: LiveData<NetworkResults<List<DepartManager>>> get() = _managerList

    private var _emplDepartList = MutableLiveData<NetworkResults<List<DepartEmployee>>>()
    val emplDepartList: LiveData<NetworkResults<List<DepartEmployee>>> get() = _emplDepartList

    suspend fun getEmployeeData(){

        _employeeList.postValue(NetworkResults.Loading())
        val employee = safeApiCall(Dispatchers.IO){
            apiService.getEmployees()
        }
        _employeeList.postValue(employee)
    }

    suspend fun getSalaryData(){
        _salaryList.postValue(NetworkResults.Loading())
        val salaries = safeApiCall(Dispatchers.IO){
            apiService.getSalaries()
        }
        _salaryList.postValue(salaries)
    }

    suspend fun getTitleData(){
        _titleList.postValue(NetworkResults.Loading())
        val title = safeApiCall(Dispatchers.IO){
            apiService.getTitles()
        }
        _titleList.postValue(title)
    }

    suspend fun getDepartmentData(){
        _departList.postValue(NetworkResults.Loading())
        val department = safeApiCall(Dispatchers.IO){
            apiService.getDepartments()
        }
        _departList.postValue(department)
    }

    suspend fun getManagerData(){
        _managerList.postValue(NetworkResults.Loading())
            val manager = safeApiCall(Dispatchers.IO){
                apiService.getDepartManager()
            }
        _managerList.postValue(manager)
    }

    suspend fun getEmplDepartment(){
        _emplDepartList.postValue(NetworkResults.Loading())
        val emplDepartment = safeApiCall(Dispatchers.IO){
            apiService.getEmployeeDepartment()
        }
        _emplDepartList.postValue(emplDepartment)
    }
}