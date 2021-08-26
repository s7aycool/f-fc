package forza.home.assignment.misc.ktx

import forza.home.assignment.data.teams.db.TeamEntity
import forza.home.assignment.data.teams.network.TeamDto
import forza.home.assignment.domain.teams.Team


fun TeamDto.toDomain() = Team(
    name = this.name,
    isNational = this.national,
    country = this.country
)

fun TeamEntity.toDomain() = Team(
    name = this.name,
    isNational = this.isNational,
    country = this.country
)

fun Team.toEntity(): TeamEntity = TeamEntity(
    name = this.name ?: "null",
    isNational = this.isNational,
    country = this.country,
)
