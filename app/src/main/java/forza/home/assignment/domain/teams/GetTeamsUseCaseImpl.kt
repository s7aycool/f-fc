package forza.home.assignment.domain.teams

import androidx.lifecycle.LiveData
import forza.home.assignment.data.teams.TeamsRepository
import forza.home.assignment.data.teams.network.NetworkFetchException
import kotlinx.coroutines.flow.Flow

class GetTeamsUseCaseImpl(private val repo: TeamsRepository): GetTeamsUseCase {
    override fun exec(): Flow<List<Team>> = repo.teams
    override val errors: LiveData<NetworkFetchException?> = repo.errors

    override suspend fun refreshTeams() = repo.refreshTeams()
}
