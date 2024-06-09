package com.example.xmlapp.Di

object HttpRoutes {

    const val baseurl="https://www.themealdb.com/api/json/v1/1"
    const val getcategories= "https://www.themealdb.com/api/json/v1/1/categories.php"
    const val getrecpiefoorcat= baseurl+"/filter.php"
    const val getrecipefulldetails= baseurl+"/lookup.php"
}