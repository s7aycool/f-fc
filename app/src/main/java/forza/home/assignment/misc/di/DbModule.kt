package forza.home.assignment.misc.di

import android.content.Context
import androidx.room.Room
import forza.home.assignment.data.teams.db.TeamsDao
import forza.home.assignment.data.teams.db.TeamsDbSource
import forza.home.assignment.data.teams.db.TeamsDbSourceImpl
import forza.home.assignment.data.teams.db.TeamsRoomDatabase
import org.koin.dsl.module

val dbModule = module {
    single { provideDb(get()) }
    single { provideTeamsDao(get()) }
    single { provideDbSource(get()) }
}

fun provideDb(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        TeamsRoomDatabase::class.java,
        "teams_database"
    ).build()

fun provideTeamsDao(db: TeamsRoomDatabase) = db.teamsDao()

fun provideDbSource(dao: TeamsDao): TeamsDbSource = TeamsDbSourceImpl(dao)