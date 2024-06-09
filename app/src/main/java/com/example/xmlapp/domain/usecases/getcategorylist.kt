package com.example.xmlapp.domain.usecases

import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.domain.repository.categroy_repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class getcategorylist (private val repository:categroy_repository) {

    suspend operator fun invoke():Flow<List<CategoryX>>{
        var a:Flow<List<CategoryX>> = flowOf(emptyList())
        repository.getcategory().collect(){
            a = flowOf(it.categories)
        }
        return a
    }
}