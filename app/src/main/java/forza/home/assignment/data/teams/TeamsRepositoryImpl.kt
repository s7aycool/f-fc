package forza.home.assignment.data.teams

import forza.home.assignment.data.teams.db.TeamsDbSource
import forza.home.assignment.data.teams.network.TeamsNetworkDataSource
import forza.home.assignment.domain.teams.Team
import forza.home.assignment.misc.utils.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class TeamsRepositoryImpl(
    private val networkSrc: TeamsNetworkDataSource,
    private val dbSrc: TeamsDbSource,
) : TeamsRepository {

    private val scope = CoroutineScope(Dispatchers.IO)
    override val teams: Flow<List<Team>> = dbSrc.getTeams()

    init {
        scope.launch(Dispatchers.IO) {
            val freshTeamsResult = networkSrc.getTeams()

            if (freshTeamsResult is Result.Success) {
                val freshTeams = freshTeamsResult.data
                freshTeams.forEach {
                    insert(it)
                }
            }
        }
    }

    override suspend fun insert(team: Team) {
        dbSrc.insert(team)
    }
}
