package forza.home.assignment.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import forza.home.assignment.domain.teams.GetTeamsUseCase
import forza.home.assignment.domain.teams.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamsViewModel(private val getTeams: GetTeamsUseCase): ViewModel() {
    val teams: LiveData<List<Team>> = getTeams.exec().asLiveData()
    val errors = getTeams.errors

    init {
        viewModelScope.launch(Dispatchers.IO) {
            refreshTeams()
        }
    }

    suspend fun refreshTeams() {
        getTeams.refreshTeams()
    }
}
