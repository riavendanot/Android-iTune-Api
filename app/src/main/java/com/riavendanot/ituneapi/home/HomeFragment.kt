package com.riavendanot.ituneapi.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riavendanot.ituneapi.R
import com.riavendanot.ituneapi.common.value
import com.riavendanot.ituneapi.databinding.FragmentHomeBinding
import com.riavendanot.ituneapi.home.adapter.SearchAdapter

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val navHosFragment by lazy {
        findNavController()
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.searchTerm(query.value())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = true
    }

    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initRecyclerView()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        setupSearchView(menu)
    }

    private fun initRecyclerView() {
        binding?.homeRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = SearchAdapter()
        }
    }

    private fun setupSearchView(menu: Menu) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = resources.getString(R.string.search_a_term)
            maxWidth = binding?.homeRecyclerView?.width ?: 0
            setOnQueryTextListener(queryTextListener)
        }
    }
}