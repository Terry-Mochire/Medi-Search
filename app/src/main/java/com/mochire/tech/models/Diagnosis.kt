package com.mochire.tech.models

data class Diagnosis(
    val conditions: List<Condition>,
    val extras: Extras,
    val has_emergency_evidence: Boolean,
    val interview_token: String,
    val question: Question
)

data class Condition(
    val common_name: String,
    val id: String,
    val name: String,
    val probability: Double
)


data class Question(
    val extras: Extras,
    val items: List<Item>,
    val text: String,
    val type: String
)

data class Item(
    val choices: List<Choice>,
    val id: String,
    val name: String
)

data class Choice(
    val id: String,
    val label: String
)