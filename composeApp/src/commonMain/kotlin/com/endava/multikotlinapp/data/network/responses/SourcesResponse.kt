package com.endava.multikotlinapp.data.network.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("sources") val sources: List<Source> = listOf(),
    @SerialName("status") val status: String = ""
)