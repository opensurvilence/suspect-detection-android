package org.yash10019coder.suspectdetectionxml.ui.suspect.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.yash10019coder.suspectdetectionxml.R
import org.yash10019coder.suspectdetectionxml.data.Result

/**
 * A fragment representing a list of Items.
 */
class ListViewSuspectsFragments : Fragment() {

    private var columnCount = 1
    private lateinit var listViewSuspectsModel: ListSuspectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_list_view_suspects_fragments_list, container, false)
        listViewSuspectsModel = ViewModelProvider(this)[ListSuspectViewModel::class.java]

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ListsViewSuspectRecyclerViewAdapter(listOf())
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            val result = listViewSuspectsModel.getSuspectList()

            if (result is Result.Success) {
                if (view is RecyclerView) {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = ListsViewSuspectRecyclerViewAdapter(result.data)
                    }
                }
            }
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListViewSuspectsFragments().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
