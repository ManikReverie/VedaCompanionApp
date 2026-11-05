package com.example.vedacompanionapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vedacompanionapp.R
import com.example.vedacompanionapp.data.FaqItem

class FaqAdapter(
    private var faqs: List<FaqItem>
) : RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    fun updateData(newFaqs: List<FaqItem>) {
        faqs = newFaqs
        expandedPositions.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val faq = faqs[position]
        val isExpanded = expandedPositions.contains(position)
        holder.bind(faq, isExpanded)
    }

    override fun getItemCount(): Int = faqs.size

    inner class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBadge: TextView = itemView.findViewById(R.id.tvItemBadge)
        private val tvExtra: TextView = itemView.findViewById(R.id.tvItemExtra)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvItemTitle)
        private val tvDesc: TextView = itemView.findViewById(R.id.tvItemDesc)
        private val tvSubtext: TextView = itemView.findViewById(R.id.tvItemSubtext)
        private val ivArrow: ImageView = itemView.findViewById(R.id.ivItemArrow)

        fun bind(faq: FaqItem, isExpanded: Boolean) {
            tvBadge.text = faq.category
            tvExtra.text = if (isExpanded) "Tap to collapse" else "Tap to read"
            tvTitle.text = faq.question

            if (isExpanded) {
                tvDesc.visibility = View.VISIBLE
                tvDesc.maxLines = Integer.MAX_VALUE
                tvDesc.text = faq.answer
                tvSubtext.visibility = View.GONE
                ivArrow.rotation = 90f
            } else {
                tvDesc.visibility = View.GONE
                tvSubtext.visibility = View.GONE
                ivArrow.rotation = 0f
            }

            itemView.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    if (expandedPositions.contains(pos)) {
                        expandedPositions.remove(pos)
                    } else {
                        expandedPositions.add(pos)
                    }
                    notifyItemChanged(pos)
                }
            }
        }
    }
}

