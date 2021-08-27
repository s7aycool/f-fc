package forza.home.assignment.data.teams.network

import android.util.Log
import forza.home.assignment.domain.teams.Team
import forza.home.assignment.misc.ktx.toDomain
import forza.home.assignment.misc.utils.Result

class TeamsNetworkDataSourceImpl(private val api: TeamsApi) : TeamsNetworkDataSource {

    override fun getTeams(): Result<List<Team>> {
        return try {
            val response = api.getTeams().execute()
            if (response.isSuccessful) {
                Log.i("TeamsNetworkDataSource","Teams are fetched: ${response.body()}")
                val list = listOf<Team>().toMutableList()
                response.body()?.forEach {
                    list.add(it.toDomain())
                }
                Result.Success(list)
            } else {
                Log.e("TeamsNetworkDataSource","Failed to fetch teams")
                Result.Error(NetworkFetchException())
            }
        } catch (e: Exception) {
            Log.e("TeamsNetworkDataSource","Error fetching teams: $e")
            Result.Error(NetworkFetchException())
        }
    }
}

class NetworkFetchException : Exception()
