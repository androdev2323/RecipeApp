package com.example.xmlapp.domain.usecases

import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.domain.model.Meal
import com.example.xmlapp.domain.model.category
import com.example.xmlapp.domain.model.recepie
import com.example.xmlapp.domain.repository.categroy_repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class getRecipieacctoCat(private val repository:categroy_repository) {
suspend operator fun invoke(category: String):Flow<List<Meal>>{
    var a:Flow<List<Meal>> = flowOf(emptyList())
repository.getrecipie(category).collect(){
         a = flowOf(it.meals)
    }
   return a;
}
}