package com.example.xmlapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xmlapp.Di.NetworkResponse
import com.example.xmlapp.domain.model.CategoryX
import com.example.xmlapp.domain.model.Meal
import com.example.xmlapp.domain.usecases.Usecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class orderingmenuViewModel @Inject constructor(val usecase: Usecase):ViewModel() {
    private var _categorylist = MutableStateFlow<latestcategroyryuistate>(latestcategroyryuistate.Success(emptyList()))
    val categorylist= _categorylist.asStateFlow()

    private var _recpielist = MutableStateFlow<latestcategroyryuistate2>(latestcategroyryuistate2.Success(
        emptyList()))
    var recpielist=_recpielist.asStateFlow()

    private var _recipedetail= MutableStateFlow<NetworkResponse<List<com.example.xmlapp.domain.model.recepiedetail.Meal>>>(NetworkResponse.loading())
    var recipedetail=_recipedetail.asStateFlow()
    init {
     viewModelScope.launch {
         _categorylist.value=latestcategroyryuistate.loadingstate
        usecase.getcategorylist().collect(){category->
            _categorylist.value =latestcategroyryuistate.Success(category)

        }
     }
    }
    fun getrecpielist(categrory:String){
        viewModelScope.launch {
            _recpielist.value=latestcategroyryuistate2.loadingstate
            usecase.getRecipieacctoCat(categrory).collect(){
                recipe->
                _recpielist.value=latestcategroyryuistate2.Success(recipe)
            }
        }

    }

    fun getrecipedetails(recipeID:Int){
        viewModelScope.launch {
            _recipedetail.value=NetworkResponse.loading()
            usecase.getrecipedetails(recipeID).collect(){
          _recipedetail.value=it

            }

        }
    }

}

sealed class latestcategroyryuistate {
    data class Success(val catgory: List<CategoryX>) : latestcategroyryuistate()
    data class Error(val exception: Throwable):latestcategroyryuistate()
    object loadingstate:latestcategroyryuistate()

}
sealed class latestcategroyryuistate2 {
    data class Success(val catgory: List<Meal>) : latestcategroyryuistate2()
    data class Error(val exception: Throwable):latestcategroyryuistate2()
    object loadingstate:latestcategroyryuistate2()

}