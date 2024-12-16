package com.anisanurjanah.khairinventory.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anisanurjanah.khairinventory.R
import com.anisanurjanah.khairinventory.data.Items
import com.anisanurjanah.khairinventory.databinding.ListItemBinding
import com.anisanurjanah.khairinventory.utils.STATUS_AVAILABLE
import com.anisanurjanah.khairinventory.utils.STATUS_NOT_AVAILABLE
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FirebaseItemAdapter(
    options: FirebaseRecyclerOptions<Items>
) : FirebaseRecyclerAdapter<Items, FirebaseItemAdapter.ItemViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        val binding = ListItemBinding.bind(view)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, model: Items) {
        holder.bind(model)
    }

    inner class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.textName.text = item.name
            binding.textStatus.text = item.status
            setTextColor(item.status, binding.textStatus)
            binding.textCategory.text = item.category
        }

        private fun setTextColor(status: String?, textView: TextView) {
            val context = textView.context
            when (status) {
                STATUS_AVAILABLE -> textView.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
                STATUS_NOT_AVAILABLE -> textView.setTextColor(ContextCompat.getColor(context, R.color.purple_200))
                else -> textView.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            textView.requestLayout()
        }
    }
}