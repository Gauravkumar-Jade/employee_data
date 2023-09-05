package com.gaurav.myemployees.common

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.viewbinding.ViewBinding
import com.gaurav.myemployees.R
import com.gaurav.myemployees.networking.NetworkResults
import com.gaurav.myemployees.utility.Const

abstract class BaseActivity<VB: ViewBinding>(
    private val bindingInflater: (inflater:LayoutInflater) -> VB):AppCompatActivity() {

    val binding: VB by lazy {
            bindingInflater(layoutInflater)
    }

    private var loadingDialog: AppCompatDialog? = null

    lateinit var mContext: Context

    open fun init(){
        setVariables()
    }

    open fun setVariables(){}

    open fun setUpViews(){}

    open fun observeData(){}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(binding.root)
        init()
        setUpViews()
        observeData()
        initLoadingDialog()
    }


    fun <T> getApiResult(
        result: NetworkResults<T>,
        loading:Boolean = true,
        handleSuccess:(T)-> Unit,
        handleErrors:(Int, String) -> Unit,
        handleException:(String) -> Unit){

        hideLoading()
        when(result){

            is NetworkResults.Success -> {
                handleSuccess.invoke(result.data!!)
            }
            is NetworkResults.Error -> {
                handleErrors.invoke(result.code, result.message!!)
            }
            is NetworkResults.Exception -> {
                handleException.invoke(result.e.message!!)
            }
            is NetworkResults.Loading -> {
                if (loading)
                    showLoading()
            }
        }

    }

    // Initialize Loading Dialog
    private fun initLoadingDialog() {
        loadingDialog = AppCompatDialog(this)
        loadingDialog!!.setCancelable(false)
        loadingDialog!!.setContentView(R.layout.dialog_loading)
        loadingDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    fun showLoading(){
        loadingDialog?.let {dialog->
            if (!dialog.isShowing){
                dialog.create()
                dialog.show()
            }
        }
    }


    fun hideLoading(){
        loadingDialog?.dismiss()
    }


    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T:Any>launchActivity(context: Context, selectedQuery:Int, value: String? = null){
        startActivity(Intent(context, T::class.java)
            .putExtra(Const.POSITION,selectedQuery)
            .putExtra(Const.VALUE,value))
    }
}