package forza.home.assignment.data.teams.network

import retrofit2.Call
import retrofit2.http.GET

interface TeamsApi {
    @GET("/forza-assignment/android/teams.json")
    fun getTeams(): Call<List<TeamDto>?>
}
