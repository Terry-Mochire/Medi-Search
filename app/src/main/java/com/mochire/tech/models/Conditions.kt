package com.mochire.tech.models

data class Conditions(
    val acuteness: String,
    val categories: List<String>,
    val common_name: String,
    val extras: Extras,
    val id: String,
    val name: String,
    val prevalence: String,
    val recommended_channel: String,
    val severity: String,
    val sex_filter: String,
    val triage_level: String
)

data class Extras(
    val hint: String,
    val icd10_code: String
)