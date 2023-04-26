package org.yash10019coder.suspectdetectionxml.ui.suspect.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.yash10019coder.suspectdetectionxml.databinding.RowListViewSuspectsFragmentsBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ListsViewSuspectRecyclerViewAdapter(
    private val values: MutableList<PlaceholderContent.PlaceholderItem>,
) : RecyclerView.Adapter<ListsViewSuspectRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowListViewSuspectsFragmentsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        holder.suspectName.text = item.content
        holder.suspectAge.text = item.details
        holder.suspectPlace.text = item.details
//        holder.suspectName.text = item.name
//        holder.suspectAge.text = item.age.toString()
//        holder.suspectPlace.text = item.info[0].location
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
