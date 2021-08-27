package forza.home.assignment.data.teams.db

import android.util.Log
import forza.home.assignment.misc.ktx.toDomain
import forza.home.assignment.misc.ktx.toEntity
import forza.home.assignment.domain.teams.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamsDbSourceImpl(private val dao: TeamsDao): TeamsDbSource {
    override fun getTeams(): Flow<List<Team>> = dao.getTeams().map {
        val list = listOf<Team>().toMutableList()
        it.forEach { teamEntity ->
            list.add(teamEntity.toDomain())
        }
        Log.d("TeamsDatabaseSource", list.toString())
        list.toList()
    }

    override suspend fun insert(team: Team) {
        dao.insert(team.toEntity())
    }
}
