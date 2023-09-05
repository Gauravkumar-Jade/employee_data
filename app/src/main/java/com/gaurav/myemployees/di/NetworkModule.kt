package com.gaurav.myemployees.di

import com.gaurav.myemployees.Controller
import com.gaurav.myemployees.networking.APIService
import com.gaurav.myemployees.utility.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideAPIService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }
}