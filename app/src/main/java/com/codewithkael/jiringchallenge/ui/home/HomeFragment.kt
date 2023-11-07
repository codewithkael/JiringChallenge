package com.codewithkael.jiringchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithkael.jiringchallenge.R
import com.codewithkael.jiringchallenge.databinding.FragmentHomeBinding
import com.codewithkael.jiringchallenge.databinding.ToolbarMainBinding
import com.codewithkael.jiringchallenge.data.remote.models.TodoModel
import com.codewithkael.jiringchallenge.ui.home.adapters.TodoRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _views: FragmentHomeBinding? = null
    private var toolbarView: ToolbarMainBinding? = null

    private val recyclerAdapter = TodoRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _views = FragmentHomeBinding.inflate(inflater, container, false)
        toolbarView = ToolbarMainBinding.bind(_views!!.root)
        return _views?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        subscribeObservers()
    }


    private fun init() {
        setupRecyclerView()
        homeViewModel.isUserSignedIn {
            if (!it) {
                findNavController().navigate(R.id.action_homeFragment_to_authFragment)
            }
        }
        toolbarView?.signOutTv?.setOnClickListener {
            homeViewModel.signOut {
                findNavController().navigate(R.id.action_global_homeFragment)
            }
        }

    }

    private fun subscribeObservers() {
        homeViewModel.userState.asLiveData().observe(viewLifecycleOwner) {
            it?.userEntity?.name?.let { username ->
                toolbarView?.usernameTv?.text = username
            }
            when (it?.todoList) {
                null -> handleTodoFetchFailed()
                else -> updateRecyclerView(it.todoList)
            }
            _views?.progressBar?.isVisible = it?.isProgressBarVisible == true
        }
    }

    private fun handleTodoFetchFailed() {
        _views?.apply {
            retryBtn.isVisible = true
            retryBtn.setOnClickListener {
                homeViewModel.getTodoList()
                retryBtn.isVisible = false
            }
        }
    }

    private fun setupRecyclerView() {
        _views?.apply {
            recyclerView.adapter = recyclerAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun updateRecyclerView(todoList: List<TodoModel>) {
        _views?.recyclerView?.isVisible = true
        recyclerAdapter.setTodoList(todoList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
        toolbarView = null
    }
}