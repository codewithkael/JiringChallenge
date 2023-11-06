package com.codewithkael.jiringchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codewithkael.jiringchallenge.R
import com.codewithkael.jiringchallenge.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var _views:FragmentHomeBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _views = FragmentHomeBinding.inflate(inflater,container,false)
        return _views?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }



    private fun init(){
        homeViewModel.isUserSignedIn {
            if (!it){
                findNavController().navigate(R.id.action_homeFragment_to_authFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
    }
}