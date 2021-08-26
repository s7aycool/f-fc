package forza.home.assignment.data.teams

import forza.home.assignment.domain.teams.Team
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {
    val teams: Flow<List<Team>>
    suspend fun insert(team: Team)
}