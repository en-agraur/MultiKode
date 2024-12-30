package com.endava.multikotlinapp.data.network.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    @SerialName("category") val category: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null,
)