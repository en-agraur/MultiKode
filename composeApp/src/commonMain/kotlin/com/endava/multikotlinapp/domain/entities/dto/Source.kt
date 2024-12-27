package com.endava.multikotlinapp.domain.entities.dto

data class Source(
    val id: String,
    val name: String,
    val description: String = "",
    val url: String = "",
    val category: String = "",
    val language: String = "",
    val country: String = ""
)