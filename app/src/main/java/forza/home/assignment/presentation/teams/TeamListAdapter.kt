package forza.home.assignment.presentation.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import forza.home.assignment.domain.teams.Team
import forza.home.assignment.presentation.teams.TeamListAdapter.TeamViewHolder
import forza.home.assignment.R

class TeamListAdapter : ListAdapter<Team, TeamViewHolder>(TeamsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            teamItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TeamViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TeamViewHolder(view)
            }
        }
    }

    class TeamsComparator : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
