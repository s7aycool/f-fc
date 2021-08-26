package forza.home.assignment.data.teams.db

import forza.home.assignment.domain.teams.Team
import kotlinx.coroutines.flow.Flow

interface TeamsDbSource {
    fun getTeams(): Flow<List<Team>>
    suspend fun insert(team: Team)
}
