package com.example.assigment_2.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment_2.databinding.DisplayFragmentBinding
import com.example.assigment_2.view.adapter.ItunesAdapter

class PopFragment : Fragment() {

    private lateinit var binding: DisplayFragmentBinding
    private val adapter: ItunesAdapter by lazy {
        ItunesAdapter(mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DisplayFragmentBinding.inflate(inflater, container, false)
        initViews()
        adapter.getItunesData(binding.root.context, "pop", 0)
        return binding.root
    }

    private fun initViews() {
        binding.apply {

            searchItunesResult.layoutManager = GridLayoutManager(context, 1)
            searchItunesResult.adapter = adapter
            searchItunesResult.addOnScrollListener(
                scrollListener
            )
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        private var visibleItemCount = 0
        private var totalItemCount = 0
        private var pastItemsVisible = 0

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            if (dy > 0) {
                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastItemsVisible = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastItemsVisible >= totalItemCount) {
                    fetchMoreData(totalItemCount)
                }
            }
        }
    }

    private fun fetchMoreData(nextStartIndex: Int) {
       adapter.getItunesData(binding.root.context, "pop", nextStartIndex)
    }
}
