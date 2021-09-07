package forza.home.assignment.data.teams

import androidx.lifecycle.LiveData
import forza.home.assignment.data.teams.network.NetworkFetchException
import forza.home.assignment.domain.teams.Team
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {
    val teams: Flow<List<Team>>
    val errors: LiveData<NetworkFetchException?>
    suspend fun insert(team: Team)
    suspend fun refreshTeams()
}