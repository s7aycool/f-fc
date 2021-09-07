package forza.home.assignment.data.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import forza.home.assignment.data.teams.db.TeamsDbSource
import forza.home.assignment.data.teams.network.NetworkFetchException
import forza.home.assignment.data.teams.network.TeamsNetworkDataSource
import forza.home.assignment.domain.teams.Team
import forza.home.assignment.misc.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TeamsRepositoryImpl(
    private val networkSrc: TeamsNetworkDataSource,
    private val dbSrc: TeamsDbSource,
) : TeamsRepository {

    private val _errors = MutableLiveData<NetworkFetchException?>(null)
    override val errors: LiveData<NetworkFetchException?> = _errors

    private val scope = CoroutineScope(Dispatchers.IO)
    override val teams: Flow<List<Team>> = dbSrc.getTeams()

    override suspend fun insert(team: Team) {
        dbSrc.insert(team)
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override suspend fun refreshTeams() {
        scope.launch(Dispatchers.IO) {
            val result = networkSrc.getTeams()

            when (result) {
                is Result.Success -> {
                    val freshTeams = result.data
                    freshTeams.forEach {
                        insert(it)
                    }
                }
                is Result.Error -> {
                    _errors.postValue(NetworkFetchException())
                }
            }
        }
    }
}
