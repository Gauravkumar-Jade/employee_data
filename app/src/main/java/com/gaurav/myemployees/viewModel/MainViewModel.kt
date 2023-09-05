package com.gaurav.myemployees.viewModel

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaurav.myemployees.database.MainDatabase
import com.gaurav.myemployees.model.*
import com.gaurav.myemployees.networking.NetworkResults
import com.gaurav.myemployees.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository, private val mainDatabase: MainDatabase):ViewModel() {


    init {
        viewModelScope.launch {
            repository.apply {
                getEmployeeData()
                getDepartmentData()
                getEmplDepartment()
                getSalaryData()
                getTitleData()
                getManagerData()
            }
        }

    }


    val employeeList:LiveData<NetworkResults<List<Employee>>> get() = repository.employeeList
    val salaryList: LiveData<NetworkResults<List<Salaries>>> get() = repository.salaryList
    val titleList:LiveData<NetworkResults<List<Title>>> get() = repository.titleList
    val departList: LiveData<NetworkResults<List<Departments>>> get() = repository.departList
    val managerList: LiveData<NetworkResults<List<DepartManager>>> get() = repository.managerList
    val emplDepartList: LiveData<NetworkResults<List<DepartEmployee>>> get() = repository.emplDepartList


    fun <T>insertData(dataList: List<T>?){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
             if (dataList != null){
                 for (i in dataList){
                     when(i){

                         is Employee -> {
                             mainDatabase.getMainDao().insertEmployee(i)
                         }
                         is Salaries -> {
                             mainDatabase.getMainDao().insertSalaries(i)
                         }
                         is Title -> {
                             mainDatabase.getMainDao().insertTitle(i)
                         }
                         is Departments -> {
                             mainDatabase.getMainDao().insertDepartment(i)
                         }
                         is DepartEmployee -> {
                             mainDatabase.getMainDao().insertEmplDepartment(i)
                         }
                         is DepartManager -> {
                             try {
                                 mainDatabase.getMainDao().insertManager(i)
                             }catch (e: SQLiteConstraintException){
                                 Log.i("MYEmp", e.printStackTrace().toString())
                             }

                         }
                     }
                 }
             }
            }
        }
    }
}