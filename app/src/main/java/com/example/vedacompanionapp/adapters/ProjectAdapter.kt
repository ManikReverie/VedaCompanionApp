package com.example.vedacompanionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.R
import com.example.vedacompanionapp.data.Project

class ProjectAdapter(
    private var projects: List<Project>,
    private val onProjectClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    fun updateData(newProjects: List<Project>) {
        projects = newProjects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.bind(project)
    }

    override fun getItemCount(): Int = projects.size

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBadge: TextView = itemView.findViewById(R.id.tvItemBadge)
        private val tvExtra: TextView = itemView.findViewById(R.id.tvItemExtra)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvItemTitle)
        private val tvDesc: TextView = itemView.findViewById(R.id.tvItemDesc)
        private val tvSubtext: TextView = itemView.findViewById(R.id.tvItemSubtext)

        fun bind(project: Project) {
            tvBadge.text = project.domain
            tvExtra.text = "${project.duration} • ${project.difficulty}"
            tvTitle.text = project.title
            tvDesc.text = project.description

            if (project.prerequisites.isNotBlank()) {
                tvSubtext.visibility = View.VISIBLE
                tvSubtext.text = "Prerequisites: ${project.prerequisites}"
            } else {
                tvSubtext.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onProjectClick(project)
            }
        }
    }
}

