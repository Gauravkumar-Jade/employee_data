package com.gaurav.myemployees.networking

import com.gaurav.myemployees.database.MainDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

sealed class NetworkResults<T> {

    class Success<T>(val data: T?):NetworkResults<T>()
    class Error<T>(val code: Int, val message: String?):NetworkResults<T>()
    class Exception<T>(val e: Throwable): NetworkResults<T>()
    class Loading<T>(): NetworkResults<T>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>): NetworkResults<T>{

    return withContext(dispatcher){
        try {
            val response = apiCall.invoke()
            val body = response.body()

            if (response.isSuccessful && body != null){
                NetworkResults.Success(body)

            }else{
                NetworkResults.Error(code = response.code(), message = response.message())
            }

        }catch (e: HttpException){
            NetworkResults.Error(code = e.code(), message = e.message())
        }catch (t: Throwable){
            NetworkResults.Exception(t)
        }
    }
}
