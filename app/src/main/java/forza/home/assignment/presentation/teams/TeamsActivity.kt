package forza.home.assignment.presentation.teams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import forza.home.assignment.R

class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel: TeamsViewModel by viewModel()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TeamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.teams.observe(this) {
            adapter.submitList(it)
        }

        viewModel.errors.observe(this) {
            it ?: return@observe

            Snackbar.make(recyclerView, "Fetch failed.", LENGTH_INDEFINITE)
                .setAction("RETRY") {
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.refreshTeams()
                    }
                }.show()
        }
    }
}
