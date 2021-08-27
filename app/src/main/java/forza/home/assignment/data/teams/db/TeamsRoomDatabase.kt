package forza.home.assignment.data.teams.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamsRoomDatabase : RoomDatabase() {
    abstract fun teamsDao(): TeamsDao
}
