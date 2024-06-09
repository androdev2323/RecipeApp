package com.example.xmlapp.domain.repository


import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.domain.model.Meal
import com.example.xmlapp.domain.model.category
import com.example.xmlapp.domain.model.recepie
import com.example.xmlapp.domain.model.recepiedetail.recipiedetail
import kotlinx.coroutines.flow.Flow

interface categroy_repository {
 suspend fun getcategory(): Flow<category>
 suspend fun getrecipie(category: String):Flow<recepie>
 suspend fun getrecipiedetails(recipe:Int):Flow<recipiedetail>
}