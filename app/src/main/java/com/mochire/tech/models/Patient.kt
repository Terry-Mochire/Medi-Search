package com.mochire.tech.models

data class Patient(
    val age: Age,
    val evidence: List<Evidence>,
    val sex: String
)

data class Age(
    val value: Int
)

data class Evidence(
    val choice_id: String,
    val id: String
)