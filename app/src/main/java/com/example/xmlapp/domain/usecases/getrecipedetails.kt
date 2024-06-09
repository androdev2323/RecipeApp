package com.example.xmlapp.domain.usecases

import android.util.Log
import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.domain.model.recepiedetail.Meal
import com.example.xmlapp.domain.repository.categroy_repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion

class getrecipedetails(private val repository:categroy_repository) {
    suspend operator fun invoke( recipeid: Int):Flow<NetworkResponse<List<Meal>>>{
      var a:Flow<NetworkResponse<List<Meal>>> = flowOf(NetworkResponse.loading())

repository.getrecipiedetails(recipeid).collect(){
    a = if(it.meals.isNotEmpty()){

        flowOf(NetworkResponse.Successfull(it.meals))
    }
    else{
        flowOf(NetworkResponse.Error(error = "something went wrong"))
    }
}


      return a
    }
}