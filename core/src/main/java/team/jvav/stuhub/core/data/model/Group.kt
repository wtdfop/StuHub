package team.jvav.stuhub.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    val name: String,
    val students: MutableList<Student>
)
