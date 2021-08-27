package forza.home.assignment.data.teams.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {

    @Query("SELECT * FROM teams_table")
    fun getTeams(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: TeamEntity)

    @Query("DELETE FROM teams_table")
    suspend fun deleteAll()
}