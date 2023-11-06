package com.codewithkael.jiringchallenge.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.codewithkael.jiringchallenge.R
import com.codewithkael.jiringchallenge.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _views: FragmentAuthBinding?=null
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _views = FragmentAuthBinding.inflate(inflater,container,false)
        return _views?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        subscribeObservers()
    }

    private fun init(){
        _views?.apply {
            loginButton.setOnClickListener {
                if (usernameEt.text.isNullOrBlank()){
                    Toast.makeText(requireContext(), "Enter username", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                authViewModel.loginWithUsername(usernameEt.text.toString())
            }
        }
    }

    private fun subscribeObservers(){
        authViewModel.loginFlow.asLiveData().observe(viewLifecycleOwner){
            when(it){
                true -> findNavController().navigate(R.id.action_global_homeFragment)
                false -> Toast.makeText(requireContext(), "Failed to find user", Toast.LENGTH_SHORT).show()
                null -> Unit
            }
            Log.d("TAG", "subscribeObservers: $it")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _views = null
    }

}