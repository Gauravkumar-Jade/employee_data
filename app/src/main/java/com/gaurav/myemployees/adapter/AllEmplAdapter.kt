package com.gaurav.myemployees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gaurav.myemployees.databinding.RawEmployeeListBinding
import com.gaurav.myemployees.model.Employee
import javax.inject.Inject

class AllEmplAdapter @Inject constructor():RecyclerView.Adapter<AllEmplAdapter.AllEmplHolder>() {

    private var employeeList: List<Employee>? = null

    fun bindEmployee(mEmployeeList: List<Employee>){
        this.employeeList = mEmployeeList
        notifyDataSetChanged()
    }

    inner class AllEmplHolder(val binding:RawEmployeeListBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllEmplHolder {
        return AllEmplHolder(RawEmployeeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount(): Int {
        return employeeList?.size?:0
    }

    override fun onBindViewHolder(holder: AllEmplHolder, position: Int) {
        val employee = employeeList?.get(position)

        holder.binding.apply {
            employee?.apply {
                txtEmpNo.text = emp_no.toString()
                txtHireDate.text = hire_date
                txtFirst.text = first_name
                txtLast.text = last_name
                txtDob.text = birth_date
                txtGender.text = gender
            }
        }
    }


}