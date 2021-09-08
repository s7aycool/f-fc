package forza.home.assignment.data.teams.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamsRoomDatabase : RoomDatabase() {

    abstract fun teamsDao(): TeamsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TeamsRoomDatabase? = null

        fun getDatabase(context: Context): TeamsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamsRoomDatabase::class.java,
                    "teams_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
