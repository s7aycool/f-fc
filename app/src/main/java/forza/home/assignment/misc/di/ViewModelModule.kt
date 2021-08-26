package forza.home.assignment.misc.di

import forza.home.assignment.presentation.teams.TeamsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TeamsViewModel(get()) }
}
