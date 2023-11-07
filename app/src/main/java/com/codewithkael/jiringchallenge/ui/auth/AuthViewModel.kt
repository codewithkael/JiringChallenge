package com.codewithkael.jiringchallenge.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithkael.jiringchallenge.data.local.LocalRepository
import com.codewithkael.jiringchallenge.data.remote.RemoteRepository
import com.codewithkael.jiringchallenge.utils.ResponseWrapper.Failure
import com.codewithkael.jiringchallenge.utils.ResponseWrapper.Success
import com.codewithkael.jiringchallenge.utils.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private var _loginFlow: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val loginFlow: StateFlow<Boolean?> = _loginFlow.asStateFlow()

    fun loginWithUsername(username: String) {
        viewModelScope.launch {
            _loginFlow.value = null
            when (val response = remoteRepository.getUserByUserName(username)) {
                is Success -> {
                    val user = response.body
                    if (user.isEmpty()){
                        _loginFlow.value = false
                    }else{
                        localRepository.insertUser(user[0].toEntity())
                        _loginFlow.value = true
                    }
                }
                is Failure -> {
                    _loginFlow.value = false
                }
            }
        }
    }
}