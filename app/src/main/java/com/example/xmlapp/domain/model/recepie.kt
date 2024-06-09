package com.example.xmlapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class recepie(
    val meals: List<Meal>
)