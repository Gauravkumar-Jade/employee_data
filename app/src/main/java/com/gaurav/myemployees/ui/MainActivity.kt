package com.gaurav.myemployees.ui

import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.gaurav.myemployees.R
import com.gaurav.myemployees.common.BaseActivity
import com.gaurav.myemployees.databinding.ActivityMainBinding
import com.gaurav.myemployees.networking.NetworkResults
import com.gaurav.myemployees.utility.Const
import com.gaurav.myemployees.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), OnClickListener {

    lateinit var viewModel: MainViewModel
    lateinit var spinnerList:Array<String>
    private var selectedQuery:Int = 0

    override fun init() {
        super.init()
    }

    override fun setVariables() {
        super.setVariables()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.listener = this
        spinnerList = resources.getStringArray(R.array.query_type)
    }

    override fun setUpViews() {
        super.setUpViews()
        setSpinnerView()

    }

    private fun setSpinnerView() {
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,spinnerList)
        binding.spinSelect.adapter = adapter

        binding.spinSelect.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedQuery = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedQuery = 0
            }

        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.employeeList.observe(this){
            getAndSaveData(it)
        }

        viewModel.salaryList.observe(this){
            getAndSaveData(it)
        }

        viewModel.titleList.observe(this){
            getAndSaveData(it)
        }
        viewModel.departList.observe(this){
            getAndSaveData(it)
        }

        viewModel.emplDepartList.observe(this){
            getAndSaveData(it)
        }

        viewModel.managerList.observe(this){
            getAndSaveData(it)
        }


    }

    private fun <T> getAndSaveData(it: NetworkResults<List<T>>) {
        getApiResult(
            it,
            handleSuccess = {
                viewModel.insertData(it)
            }, handleErrors = { code, message ->
                val error = "$code -> $message"
                showToast(error)
            }, handleException = { message ->
                showToast(message)
            })
        }

    override fun onClick(view: View?) {
        binding.apply {
            when(view){

               btSubmit -> {
                   val value = getEditTextValue()
                   if (value?.isNotEmpty()!! || value.isNotBlank()){
                       launchActivity<DetailsActivity>(this@MainActivity,selectedQuery,value)
                   }else {
                       showToast("Please enter value")
                   }
               }
                btViewAllEmpl -> {
                    launchActivity<DetailsActivity>(this@MainActivity, Const.GET_ALL_EMPLOYEES)
                }
               btViewAllDept -> {
                   launchActivity<DetailsActivity>(this@MainActivity,Const.GET_ALL_DEPARTMENTS)
               }
            }
        }
    }


    private fun getEditTextValue(): String? {
        return binding.etQuery.text?.toString()
    }
}