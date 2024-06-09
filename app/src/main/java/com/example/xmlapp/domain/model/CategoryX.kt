package com.example.xmlapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryX(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)