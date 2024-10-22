package team.jvav.stuhub.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String
)
