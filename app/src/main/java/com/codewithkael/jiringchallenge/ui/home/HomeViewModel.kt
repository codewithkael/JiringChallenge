package com.codewithkael.jiringchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithkael.jiringchallenge.local.LocalRepository
import com.codewithkael.jiringchallenge.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private var _userState : MutableStateFlow<UserState?> = MutableStateFlow(null)
    val userState : StateFlow<UserState?> = _userState.asStateFlow()
    private fun getCurrentUser() = _userState.value

    fun isUserSignedIn(isSignedIn:(Boolean)->Unit){
        viewModelScope.launch {
            val user = localRepository.getCurrentUser()
            if (user!=null){
                _userState.emit(getCurrentUser()?.copy(userEntity = user))
                isSignedIn(true)
            }else{
                isSignedIn(false)
            }
        }
    }
}