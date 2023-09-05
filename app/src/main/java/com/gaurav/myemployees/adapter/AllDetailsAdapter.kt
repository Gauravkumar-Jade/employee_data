package com.gaurav.myemployees.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gaurav.myemployees.databinding.RawDetailsListBinding
import com.gaurav.myemployees.model.*
import javax.inject.Inject

class AllDetailsAdapter @Inject constructor():RecyclerView.Adapter<AllDetailsAdapter.AllDetailsHolder>() {

    private var dataList: List<Any>?= null
    private var dataClass: Class<*>? = null

    fun bindData(mDataList: List<Any>?, dataClass: Class<*>?){
        this.dataList = mDataList
        this.dataClass = dataClass
        notifyDataSetChanged()
    }

    inner class AllDetailsHolder(val binding:RawDetailsListBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDetailsHolder {
        return AllDetailsHolder(RawDetailsListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return dataList?.size?:0
    }

    override fun onBindViewHolder(holder: AllDetailsHolder, position: Int) {
        val data = dataList?.get(position)
        when(dataClass){

            Title::class.java -> {addTitleData(holder, data as Title)}
            Salaries::class.java -> {addSalaryData(holder, data as Salaries)}
            DepartEmployee::class.java -> {addDepartData(holder, data as DepartEmployee)}
            DepartManager::class.java -> {addManagerData(holder, data as DepartManager)}
            Departments::class.java -> {addAllDepartData(holder, data as Departments)}
        }
    }

    private fun addAllDepartData(holder: AllDetailsHolder, departments: Departments) {
        holder.binding.apply {
            llDeptNo.visibility = View.VISIBLE
            llDeptName.visibility = View.VISIBLE
            llDate.visibility = View.GONE
            txtDeptNo.text =departments.dept_no
            txtDeptName.text = departments.dept_name
        }
    }

    private fun addManagerData(holder: AllDetailsHolder, departManager: DepartManager) {
        holder.binding.apply {
            llEmployee.visibility = View.VISIBLE
            llDeptNo.visibility = View.VISIBLE
            txtEmpNo.text = departManager.emp_no.toString()
            txtDeptNo.text = departManager.dept_no
            txtFromDate.text =  departManager.from_date
            txtToDate.text =  departManager.to_date
        }
    }

    private fun addDepartData(holder: AllDetailsHolder, departments: DepartEmployee) {
        holder.binding.apply {
            llEmployee.visibility = View.VISIBLE
            llDeptNo.visibility = View.VISIBLE
            txtEmpNo.text = departments.emp_no.toString()
            txtDeptNo.text = departments.dept_no.toString()
            txtFromDate.text =  departments.from_date
            txtToDate.text =  departments.to_date
        }
    }

    private fun addSalaryData(holder: AllDetailsHolder, salaries: Salaries) {
        holder.binding.apply {
            llEmployee.visibility = View.VISIBLE
            llSalary.visibility = View.VISIBLE
            txtEmpNo.text = salaries.emp_no.toString()
            txtSalary.text = salaries.salary.toString()
            txtFromDate.text =  salaries.from_date
            txtToDate.text =  salaries.to_date
        }
    }

    private fun addTitleData(holder: AllDetailsHolder, title: Title) {

        holder.binding.apply {
            llEmployee.visibility = View.VISIBLE
            llTitle.visibility = View.VISIBLE
            txtEmpNo.text = title.emp_no.toString()
            txtTitle.text = title.title
            txtFromDate.text = title.from_date
            txtToDate.text = title.to_date
        }

    }

}