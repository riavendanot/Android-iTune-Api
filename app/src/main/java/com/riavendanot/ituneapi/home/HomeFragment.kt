package com.riavendanot.ituneapi.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riavendanot.ituneapi.R
import com.riavendanot.ituneapi.common.extension.value
import com.riavendanot.ituneapi.databinding.FragmentHomeBinding
import com.riavendanot.ituneapi.dialog.ErrorDialogFragment
import com.riavendanot.ituneapi.domain.entity.ResultDto
import com.riavendanot.ituneapi.home.adapter.ResultDelegateAdapter
import com.riavendanot.ituneapi.home.adapter.SearchAdapter
import com.riavendanot.ituneapi.home.adapter.base.InfinityScrollListener
import com.riavendanot.ituneapi.home.viewmodel.HomeViewModel
import com.riavendanot.ituneapi.home.viewmodel.HomeViewModelFactory

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels{ HomeViewModelFactory() }
    private val navHosFragment by lazy {
        findNavController()
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchView?.clearFocus()
            searchView?.onActionViewCollapsed()
            viewModel.searchTerm(query.value())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = true
    }

    private val observerProgress = Observer<Boolean>{
        binding?.homeProgressBar?.visibility = if(it) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private val observerAddSearch = Observer<List<ResultDto>?> {
        if (!it.isNullOrEmpty()) {
            val flag = viewModel.resetList.value ?: false
            if (flag) {
                viewModel.changeResetList()
                searchAdapter.resetItems(it)
            } else {
                searchAdapter.refreshItems(it)
            }
        }
    }

    private val observerMessage = Observer<String> {
        val flag = viewModel.showDialog.value ?: false
        if (!it.isNullOrEmpty() && flag) {
            viewModel.changeDialogState()
            navHosFragment.navigate(
                R.id.errorDialogFragment,
                bundleOf(ErrorDialogFragment.DIALOG_BUNDLE_KEY to it))
        }
    }

    private val itemClick = object : ResultDelegateAdapter.OnViewSelectedListener{
        override fun onItemSelected(item: ResultDto) {
            val action = HomeFragmentDirections.actionHomeToDetail(item)
            navHosFragment.navigate(action)
        }

    }

    private var binding: FragmentHomeBinding? = null
    private var searchView: SearchView? = null
    private val searchAdapter = SearchAdapter(itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewModel.loader.observe(viewLifecycleOwner, observerProgress)
        viewModel.list.observe(viewLifecycleOwner, observerAddSearch)
        viewModel.message.observe(viewLifecycleOwner, observerMessage)
        initRecyclerView()
    }

    override fun onDestroyView() {
        binding = null
        searchView = null
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        setupSearchView(menu)
    }

    private fun initRecyclerView() {
        binding?.homeRecyclerView?.apply {
            val linearLayout = LinearLayoutManager(context)
            setHasFixedSize(true)
            layoutManager = linearLayout
            adapter = searchAdapter
            clearOnScrollListeners()
            addOnScrollListener(InfinityScrollListener(
                { viewModel.requestTerm() },
                linearLayout)
            )
        }
    }

    private fun setupSearchView(menu: Menu) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = resources.getString(R.string.search_a_term)
            maxWidth = binding?.homeRecyclerView?.width ?: 0
            setOnQueryTextListener(queryTextListener)
        }
    }
}