package forza.home.assignment.domain.teams

import kotlinx.coroutines.flow.Flow

interface GetTeamsUseCase {
    fun exec(): Flow<List<Team>>
}
