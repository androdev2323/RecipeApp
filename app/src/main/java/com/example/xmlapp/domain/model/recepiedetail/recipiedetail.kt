package com.example.xmlapp.domain.model.recepiedetail

import kotlinx.serialization.Serializable

@Serializable
data class recipiedetail(
    val meals: List<Meal>
)