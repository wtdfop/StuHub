package team.jvav.stuhub.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Class(
    val id: Int,
    val groups: MutableList<Group>,
    val students: MutableList<Student>
)
