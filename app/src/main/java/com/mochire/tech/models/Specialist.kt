package com.mochire.tech.models

data class Specialist(
    val recommended_channel: String,
    val recommended_specialist: RecommendedSpecialist
)

data class RecommendedSpecialist(
    val id: String,
    val name: String
)