package forza.home.assignment.domain.teams

import forza.home.assignment.data.teams.TeamsRepository
import kotlinx.coroutines.flow.Flow

class GetTeamsUseCaseImpl(private val repo: TeamsRepository): GetTeamsUseCase {
    override fun exec(): Flow<List<Team>> = repo.teams
}
