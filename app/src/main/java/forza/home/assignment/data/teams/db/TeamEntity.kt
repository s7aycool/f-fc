package forza.home.assignment.data.teams.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams_table")
class TeamEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val isNational: Boolean?,
    val country: String?,
)