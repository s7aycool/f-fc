package forza.home.assignment.misc.di

import forza.home.assignment.data.teams.TeamsRepository
import forza.home.assignment.data.teams.TeamsRepositoryImpl
import forza.home.assignment.data.teams.db.TeamsDbSource
import forza.home.assignment.data.teams.network.TeamsNetworkDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { provideRepository(get(), get()) }
}

fun provideRepository(
    networkSrc: TeamsNetworkDataSource,
    dbSrc: TeamsDbSource,
): TeamsRepository = TeamsRepositoryImpl(networkSrc, dbSrc)

