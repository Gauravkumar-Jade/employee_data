package com.gaurav.myemployees.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.myemployees.adapter.AllDetailsAdapter
import com.gaurav.myemployees.adapter.AllEmplAdapter
import com.gaurav.myemployees.common.BaseActivity
import com.gaurav.myemployees.databinding.ActivityDetailsBinding
import com.gaurav.myemployees.model.*
import com.gaurav.myemployees.utility.Const
import com.gaurav.myemployees.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>(ActivityDetailsBinding::inflate) {

    private var selectedQuery: Int = 0
    private var queryValue: String? = null
    private var layoutManager:LinearLayoutManager? = null

    @Inject
    lateinit var allEmplAdapter: AllEmplAdapter

    @Inject
    lateinit var allDetailsAdapter: AllDetailsAdapter

    lateinit var viewModel: DetailViewModel


    override fun init() {
        super.init()

        selectedQuery = intent.extras?.getInt(Const.POSITION)!!
        queryValue = intent.extras?.getString(Const.VALUE)
    }

    override fun setVariables() {
        super.setVariables()

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
    }

    override fun setUpViews() {
        super.setUpViews()
        setAdapter()
    }

    private fun setAdapter() {
        when(selectedQuery){

            Const.GET_EMPLOYEE_WITH_TITLE -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getEmpWithTitles(queryValue!!)
            }
            Const.GET_EMPLOYEE_WITH_SALARIES -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getEmpWithSalaries(queryValue!!)
            }

            Const.GET_EMPLOYEE_WITH_DEPARTMENT -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getEmpWithDepartments(queryValue!!)
            }

            Const.GET_DEPARTMENT_WITH_MANAGER -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getDepartmentWithManager(queryValue!!)
            }

            Const.GET_DEPARTMENT_WITH_EMPLOYEE -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getDepartmentWithDepartEmp(queryValue!!)
            }

            Const.GET_ALL_EMPLOYEES -> {
                binding.rvList.adapter = allEmplAdapter
                viewModel.getAllEmployees()}

            Const.GET_ALL_DEPARTMENTS -> {
                binding.rvList.adapter = allDetailsAdapter
                viewModel.getAllDepartments()
            }
        }
    }

    override fun observeData() {
        super.observeData()

        when(selectedQuery){


            Const.GET_EMPLOYEE_WITH_TITLE  -> {
                viewModel.empWithTitle.observe(this){
                    if(it != null){
                        allDetailsAdapter.bindData(it.title, Title::class.java)
                        val empl = it.employee
                        bindEmplData("Title",empl)
                    }else{
                        showError()
                    }
                }
            }

            Const.GET_EMPLOYEE_WITH_SALARIES -> {
                viewModel.empWithSalary.observe(this){
                    if(it != null){
                        allDetailsAdapter.bindData(it.salaries, Salaries::class.java)
                        val empl = it.employee
                        bindEmplData("Salary",empl)
                    }else{
                        showError()
                    }
                }
            }

            Const.GET_EMPLOYEE_WITH_DEPARTMENT -> {
                viewModel.empWithDepartments.observe(this){
                    if(it != null){
                        allDetailsAdapter.bindData(it.departEmployee, DepartEmployee::class.java)
                        val empl = it.employee
                        bindEmplData("Department",empl)
                    }else{
                        showError()
                    }
                }
            }

            Const.GET_DEPARTMENT_WITH_MANAGER -> {
                viewModel.departWithManager.observe(this){
                    if(it != null){
                        allDetailsAdapter.bindData(it.departManager, DepartManager::class.java)
                        val dept= it.departments
                        bindDeptData("Manager",dept)
                    }else{
                        showError()
                    }
                }

            }
            Const.GET_DEPARTMENT_WITH_EMPLOYEE -> {

                viewModel.departWithEmployee.observe(this){
                    if(it != null){
                        allDetailsAdapter.bindData(it.departEmployee, DepartEmployee::class.java)
                        val dept= it.departments
                        bindDeptData("Employee",dept)
                    }else{
                        showError()
                    }
                }

            }

            Const.GET_ALL_EMPLOYEES -> {
                lifecycleScope.launch{
                    viewModel.employeeList.observe(this@DetailsActivity){
                        allEmplAdapter.bindEmployee(it)
                    }
                }}

            Const.GET_ALL_DEPARTMENTS -> {
                viewModel.departList.observe(this@DetailsActivity){
                    allDetailsAdapter.bindData(it, Departments::class.java)
                }
            }

        }
    }

    private fun showError() {
        binding.apply {
            llParent.visibility = View.GONE
            txtError.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindDeptData(label: String, dept: Departments?) {
        if(dept != null){
            binding.llDept.visibility = View.VISIBLE
            binding.llLabels.visibility = View.VISIBLE
            binding.txtLabel.text = "$label details for Dept No: ${dept.dept_no}"

            binding.department.apply {
                txtDeptNo.text = dept.dept_no
                txtDeptName.text = dept.dept_name
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun bindEmplData(label:String, empl: Employee?) {
        if(empl != null){
            binding.llEmpl.visibility = View.VISIBLE
            binding.llLabels.visibility = View.VISIBLE
            binding.txtLabel.text = "$label details for Emp No: ${empl.emp_no}"
            binding.employee.apply {
                txtEmpNo.text = empl.emp_no.toString()
                txtGender.text = empl.gender
                txtDob.text = empl.birth_date
                txtLast.text = empl.last_name
                txtFirst.text = empl.first_name
                txtHireDate.text = empl.hire_date
            }
        }

    }
}