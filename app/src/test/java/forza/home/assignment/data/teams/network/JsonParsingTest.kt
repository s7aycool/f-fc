package forza.home.assignment.data.teams.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import forza.home.assignment.data.teams.network.TeamsTestResponses.Companion as mock

class JsonParsingTest {
    private val moshi = Moshi.Builder().build()
    private var type = Types.newParameterizedType(MutableList::class.java, TeamDto::class.java)
    private var adapter: JsonAdapter<List<TeamDto>> = moshi.adapter(type)

    @Test
    fun moshiJsonParser_oneTeamResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.oneTeamResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`("Arsenal FC"))
        assertThat(teamDto?.national, `is`(false))
        assertThat(teamDto?.country, `is`("England"))
    }

    @Test
    fun moshiJsonParser_nullNameTeamResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.nullNameTeamResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`(nullValue()))
        assertThat(teamDto?.national, `is`(false))
        assertThat(teamDto?.country, `is`("England"))
    }

    @Test
    fun moshiJsonParser_allNullsTeamResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.allNullsTeamResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`(nullValue()))
        assertThat(teamDto?.national, `is`(nullValue()))
        assertThat(teamDto?.country, `is`(nullValue()))
    }

    @Test
    fun moshiJsonParser_emptyObjectInArrayResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.emptyObjectInArrayResponse)
        println("EmptyObjectInArrayRespone: Parsed to ${result.toString()}")

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`(nullValue()))
        assertThat(teamDto?.national, `is`(nullValue()))
        assertThat(teamDto?.country, `is`(nullValue()))
    }

    @Test
    fun moshiJsonParser_emptyArrayResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.emptyArrayResponse)
        println("EmptyArrayResponse: Parsed to ${result.toString()}")

        assertThat(result, `is`(emptyList()))
    }

    @Test
    fun moshiJsonParser_teamWithoutNameResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.teamWithoutNameResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`(nullValue()))
        assertThat(teamDto?.national, `is`(false))
        assertThat(teamDto?.country, `is`("England"))
    }

    @Test
    fun moshiJsonParser_teamWithExtraFieldsResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.teamWithExtraFieldsResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`("Arsenal FC"))
        assertThat(teamDto?.national, `is`(false))
        assertThat(teamDto?.country, `is`("England"))
    }

    @Test
    fun moshiJsonParser_teamWithDuplicatedFieldResponse_ParseSuccessfully() {
        val result = adapter.fromJson(mock.teamWithDuplicatedFieldResponse)

        val teamDto = result?.get(0)

        assertThat(teamDto?.name, `is`("Arsenal FC"))
        assertThat(teamDto?.national, `is`(false))
        assertThat(teamDto?.country, `is`("England"))
    }
}
