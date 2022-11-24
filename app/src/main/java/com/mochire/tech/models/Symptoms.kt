package com.mochire.tech.models

data class Symptoms(
    val category: String,
    val children: List<Any>,
    val common_name: String,
    val extras: Extras,
    val id: String,
    val image_source: Any,
    val image_url: Any,
    val name: String,
    val parent_id: Any,
    val parent_relation: Any,
    val seriousness: String,
    val sex_filter: String
)

