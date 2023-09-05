package com.gaurav.myemployees.viewModel

import android.os.Build.VERSION_CODES.M
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaurav.myemployees.database.MainDatabase
import com.gaurav.myemployees.model.Departments
import com.gaurav.myemployees.model.Employee
import com.gaurav.myemployees.model.Title
import com.gaurav.myemployees.model.relation.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val mainDatabase: MainDatabase):ViewModel() {

    private var _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>> get() = _employeeList

    private var _departList = MutableLiveData<List<Departments>>()
    val departList: LiveData<List<Departments>> get() = _departList

    private var _empWithTitle = MutableLiveData<EmployeeWithTitle>()
    val empWithTitle: LiveData<EmployeeWithTitle> get() = _empWithTitle

    private var _empWithSalary = MutableLiveData<EmployeeWithSalaries>()
    val empWithSalary: LiveData<EmployeeWithSalaries> get() = _empWithSalary

    private var _empWithDepartments = MutableLiveData<EmployeeWithDepartment>()
    val empWithDepartments: LiveData<EmployeeWithDepartment> get() = _empWithDepartments

    private var _departWithManager = MutableLiveData<DepartmentWithDepartManager>()
    val departWithManager: LiveData<DepartmentWithDepartManager> get() = _departWithManager


    private var _departWithEmployee = MutableLiveData<DepartmentWithDepartEmployee>()
    val departWithEmployee: LiveData<DepartmentWithDepartEmployee> get() = _departWithEmployee


    fun getAllEmployees(){
        viewModelScope.launch(Dispatchers.IO){
            async {
                val employee = mainDatabase.getMainDao().getAllEmployee()
                _employeeList.postValue(employee)
            }.await()
        }
    }


    fun getEmpWithTitles(emp_no:String){
        viewModelScope.launch(Dispatchers.IO){
            async {
                val title = mainDatabase.getMainDao().getEmployeeWithTitle(emp_no)
                _empWithTitle.postValue(title)
            }.await()
        }
    }

    fun getEmpWithSalaries(emp_no:String){
        viewModelScope.launch(Dispatchers.IO){
            async {
                val salaries = mainDatabase.getMainDao().getEmployeeWithSalary(emp_no)
                _empWithSalary.postValue(salaries)
            }.await()
        }
    }

    fun getEmpWithDepartments(emp_no:String){
        viewModelScope.launch(Dispatchers.IO){
            async {
                val department = mainDatabase.getMainDao().getEmployeeWithDepartment(emp_no)
                _empWithDepartments.postValue(department)
            }.await()
        }
    }

    fun getDepartmentWithManager(dept_no:String){
        viewModelScope.launch(Dispatchers.IO) {
            async {
                val manager = mainDatabase.getMainDao().getDepartmentWithManager(dept_no)
                _departWithManager.postValue(manager)
            }.await()
        }
    }

    fun getDepartmentWithDepartEmp(dept_no:String){
        viewModelScope.launch(Dispatchers.IO) {
            async {
                val empl = mainDatabase.getMainDao().getDepartmentWithDepartEmployee(dept_no)
                _departWithEmployee.postValue(empl)
            }.await()
        }
    }

    fun getAllDepartments(){
        viewModelScope.launch(Dispatchers.IO) {
            async {
                val dept = mainDatabase.getMainDao().getDepartment()
                _departList.postValue(dept)
            }.await()
        }
    }
}