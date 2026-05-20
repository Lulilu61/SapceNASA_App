package fr.sdv.b32526.spacenasa_app.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstrosResponse(
    @SerialName("number") val totalAstronauts: Int,
    @SerialName("people") val astronautsList: List<AstronautEntity>,
    @SerialName("message") val status: String
)

@Serializable
data class AstronautEntity(
    @SerialName("name") val fullName: String,
    @SerialName("craft") val spaceCraft: String
)