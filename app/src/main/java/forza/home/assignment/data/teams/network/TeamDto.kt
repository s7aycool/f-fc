package forza.home.assignment.data.teams.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamDto(
    val name: String?,
    val national: Boolean?,
    @field:Json(name = "country_name")
    val country: String?,
)
