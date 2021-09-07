package forza.home.assignment.domain.teams

import androidx.lifecycle.LiveData
import forza.home.assignment.data.teams.network.NetworkFetchException
import kotlinx.coroutines.flow.Flow

interface GetTeamsUseCase {
    val errors: LiveData<NetworkFetchException?>
    fun exec(): Flow<List<Team>>
    suspend fun refreshTeams()
}
