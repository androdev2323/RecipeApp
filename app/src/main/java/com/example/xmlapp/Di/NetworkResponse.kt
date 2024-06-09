package com.example.xmlapp.Di

sealed class NetworkResponse<T> (val data:T?=null,val error:String?=null){
 class loading<T>():NetworkResponse<T>()
     class Successfull<T>( data: T):NetworkResponse<T>(data)
    class Error<T>(data: T?=null,error:String):NetworkResponse<T>(data,error)
}