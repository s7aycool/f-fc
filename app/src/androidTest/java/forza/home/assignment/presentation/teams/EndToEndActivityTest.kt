package forza.home.assignment.presentation.teams

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import forza.home.assignment.R
import forza.home.assignment.data.teams.network.TeamsAndroidTestResponse.Companion as mock

class EndToEndActivityTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var activityRule: ActivityScenarioRule<TeamsActivity> =
        ActivityScenarioRule(TeamsActivity::class.java)

    private var server = MockWebServer()
    private val mockedResponse = MockResponse()
        .setBody(mock.normalResponse)
        .addHeader("Content-Type", "application/json")

    @Before
    fun setup() {
        server.start(8080)
        server.enqueue(mockedResponse)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun recyclerViewItems_fourTeams_allArePresent() {
        // launch TeamsActivity
        val scenario = launchActivity<TeamsActivity>()

        // move to onResume()
        scenario.moveToState(Lifecycle.State.RESUMED)

        // check that recycler view contains all teams from mocked response
        onView(RecyclerViewMatcher(R.id.recyclerview)
            .atPositionOnView(0, R.id.textView))
            .check(matches(withText("Arsenal FC")))

        onView(RecyclerViewMatcher(R.id.recyclerview)
            .atPositionOnView(1, R.id.textView))
            .check(matches(withText("FC Barcelona")))

        onView(RecyclerViewMatcher(R.id.recyclerview)
            .atPositionOnView(2, R.id.textView))
            .check(matches(withText("Sweden")))

        onView(RecyclerViewMatcher(R.id.recyclerview)
            .atPositionOnView(3, R.id.textView))
            .check(matches(withText("Inter Milan")))
    }
}
