package forza.home.assignment.misc.di

import forza.home.assignment.data.teams.TeamsRepository
import forza.home.assignment.domain.teams.GetTeamsUseCaseImpl
import forza.home.assignment.domain.teams.GetTeamsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetTeamsUseCase(get()) }
}

fun provideGetTeamsUseCase(repo: TeamsRepository): GetTeamsUseCase = GetTeamsUseCaseImpl(repo)
