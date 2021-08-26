package forza.home.assignment.presentation.teams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import stay.cool.assignment.R

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
    }
}
