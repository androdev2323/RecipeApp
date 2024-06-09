package com.example.xmlapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class category(
    val categories: List<CategoryX>
)