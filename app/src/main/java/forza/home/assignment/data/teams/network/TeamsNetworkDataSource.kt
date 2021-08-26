package forza.home.assignment.data.teams.network

import forza.home.assignment.domain.teams.Team
import forza.home.assignment.misc.utils.Result

interface TeamsNetworkDataSource {
    fun getTeams(): Result<List<Team>>
}
