package forza.home.assignment.data.teams

import forza.home.assignment.data.teams.db.TeamEntity
import forza.home.assignment.data.teams.network.TeamDto
import forza.home.assignment.misc.ktx.toDomain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class MapperTests {
    @Test
    fun toDomainKtx_InterMilan_MapsSuccessfully() {
        val team = TeamDto(
            name = "Inter Milan",
            national = false,
            country = "Italy"
        )

        val result = team.toDomain()

        assertThat(result.name, `is`("Inter Milan"))
        assertThat(result.isNational, `is`(false))
        assertThat(result.country, `is`("Italy"))
    }

    @Test
    fun toDomainKtx_Nulls_MapsSuccessfullyToTeamOfNulls() {
        val team = TeamDto(
            name = null,
            national = null,
            country = null
        )

        val result = team.toDomain()

        assertThat(result.name, isEmptyOrNullString())
        assertThat(result.isNational, `is`(nullValue()))
        assertThat(result.country,  isEmptyOrNullString())
    }

    @Test
    fun toDomainKtx_EmptyStrings_MapsSuccessfully() {
        val team = TeamDto(
            name = "",
            national = false,
            country = ""
        )

        val result = team.toDomain()

        assertThat(result.name, isEmptyOrNullString())
        assertThat(result.isNational, `is`(false))
        assertThat(result.country,  isEmptyOrNullString())
    }

    @Test
    fun toEntityKtx_EmptyStrings_MapsSuccessfully() {
        val team = TeamEntity(
            name = "Forza FC",
            isNational = false,
            country = "Sweden"
        )

        val result = team.toDomain()

        assertThat(result.name,  `is`("Forza FC"))
        assertThat(result.isNational, `is`(false))
        assertThat(result.country,  `is`("Sweden"))
    }
}
