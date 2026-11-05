package com.example.vedacompanionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.R
import com.example.vedacompanionapp.data.Domain

class DomainAdapter(
    private var domains: List<Domain>,
    private val onDomainClick: (Domain) -> Unit
) : RecyclerView.Adapter<DomainAdapter.DomainViewHolder>() {

    fun updateData(newDomains: List<Domain>) {
        domains = newDomains
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return DomainViewHolder(view)
    }

    override fun onBindViewHolder(holder: DomainViewHolder, position: Int) {
        val domain = domains[position]
        holder.bind(domain)
    }

    override fun getItemCount(): Int = domains.size

    inner class DomainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBadge: TextView = itemView.findViewById(R.id.tvItemBadge)
        private val tvExtra: TextView = itemView.findViewById(R.id.tvItemExtra)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvItemTitle)
        private val tvDesc: TextView = itemView.findViewById(R.id.tvItemDesc)
        private val tvSubtext: TextView = itemView.findViewById(R.id.tvItemSubtext)

        fun bind(domain: Domain) {
            tvBadge.text = "Track"
            tvExtra.text = "${domain.projectCount} Projects"
            tvTitle.text = domain.name
            tvDesc.text = domain.description
            tvSubtext.visibility = View.VISIBLE
            tvSubtext.text = "Tech: ${domain.techStack}"

            itemView.setOnClickListener {
                onDomainClick(domain)
            }
        }
    }
}

