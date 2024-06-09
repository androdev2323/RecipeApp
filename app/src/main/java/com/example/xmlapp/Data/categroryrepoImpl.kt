package com.example.xmlapp.Data

import com.example.xmlapp.Di.HttpRoutes
import com.example.xmlapp.Di.HttpRoutes.baseurl
import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.domain.model.category
import com.example.xmlapp.domain.model.recepie
import com.example.xmlapp.domain.model.recepiedetail.recipiedetail
import com.example.xmlapp.domain.repository.categroy_repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class categoryrepoImpl ( private val client: HttpClient):categroy_repository {

    override suspend fun getcategory():Flow<category> {
       val a=client.get {
            url(HttpRoutes.getcategories)


        }
        return  flowOf(a.body())


    }

    override suspend fun getrecipie(category: String): Flow<recepie> {
        return flowOf(client.get {
            url(HttpRoutes.getrecpiefoorcat+"?c=$category")
        }.body())
    }

    override suspend fun getrecipiedetails(recipe: Int):Flow<recipiedetail> {
val response =client.get {
    url(HttpRoutes.getrecipefulldetails+"?i=$recipe")
}
        return flowOf(response.body())

    }
}