package forza.home.assignment.data.teams

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import forza.home.assignment.domain.teams.Team
import forza.home.assignment.misc.util.getOrAwaitValue
import forza.home.assignment.presentation.teams.TeamsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.test.KoinTest
import org.koin.test.inject
import forza.home.assignment.data.teams.network.TeamsAndroidTestResponse.Companion as mock

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DataSourceTests : KoinTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel: TeamsViewModel by inject()

    private var server = MockWebServer()
    private val mockedResponse = MockResponse()
        .setBody(mock.normalResponse)
        .addHeader("Content-Type", "application/json")

    @Before
    fun setup() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(
            "pm clear forza.home.assignment.test"
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    // a) assert that db is empty before fetching from mocked server
    fun a_teamsDbSource_beforeFetchFromMockedServer_noData() {
        val teams = viewModel.teams.getOrAwaitValue()

        assertThat(teams, `is`(emptyList()))
    }

    @Test
    // b) fetch mocked response, assert that it's parsed as expected
    // and also fetched response will be inserted into db by repository
    fun b_testNetworkDataSource_useMockWebServer_passCorrect() {
        server.enqueue(mockedResponse)

        viewModel.teams.observeForever { teams ->
            teams ?: return@observeForever
            if (teams == emptyList<Team>()) return@observeForever

            assertThat(teams[0].name, `is`("Arsenal FC"))
            assertThat(teams[1].name, `is`("FC Barcelona"))
            assertThat(teams[2].name, `is`("Sweden"))
            assertThat(teams[3].name, `is`("Inter Milan"))
        }
    }

    @Test
    // c) assert that mocked response is stored in db after fetching from mocked server
    fun c_teamsVmLivedataFromDb_afterFetchFromMockedResponse_passedCorrectly() {
        viewModel.teams.observeForever { teams ->
            teams ?: return@observeForever
            if (teams == emptyList<Team>()) return@observeForever

            assertThat(teams[0].name, `is`("Arsenal FC"))
            assertThat(teams[1].name, `is`("FC Barcelona"))
            assertThat(teams[2].name, `is`("Sweden"))
            assertThat(teams[3].name, `is`("Inter Milan"))
        }
    }
}
