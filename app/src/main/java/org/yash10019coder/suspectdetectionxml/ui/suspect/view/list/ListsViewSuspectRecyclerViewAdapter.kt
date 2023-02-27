package org.yash10019coder.suspectdetectionxml.ui.suspect.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.yash10019coder.suspectdetectionxml.databinding.RowListViewSuspectsFragmentsBinding
import org.yash10019coder.suspectdetectionxml.ui.suspect.view.list.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ListsViewSuspectRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
) : RecyclerView.Adapter<ListsViewSuspectRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(RowListViewSuspectsFragmentsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.suspectName.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: RowListViewSuspectsFragmentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val suspectName = binding.tvSuspectName
        val suspectAge = binding.tvSuspectAge
        val suspectPlace = binding.tvSuspectPlace
    }

}
