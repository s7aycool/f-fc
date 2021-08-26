package forza.home.assignment.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import forza.home.assignment.domain.teams.GetTeamsUseCase
import forza.home.assignment.domain.teams.Team

class TeamsViewModel (getTeams: GetTeamsUseCase): ViewModel() {
    val teams: LiveData<List<Team>> = getTeams.exec().asLiveData()
}
