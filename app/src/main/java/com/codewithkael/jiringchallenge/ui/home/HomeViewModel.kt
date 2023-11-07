package com.codewithkael.jiringchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithkael.jiringchallenge.data.local.LocalRepository
import com.codewithkael.jiringchallenge.data.remote.RemoteRepository
import com.codewithkael.jiringchallenge.utils.ResponseWrapper.Failure
import com.codewithkael.jiringchallenge.utils.ResponseWrapper.Success
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

    private var _userState: MutableStateFlow<UserState?> = MutableStateFlow(UserState())
    val userState: StateFlow<UserState?> = _userState.asStateFlow()
    private fun getCurrentUser() = _userState.value

    fun isUserSignedIn(isSignedIn: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = localRepository.getCurrentUser()
            if (user != null) {
                _userState.emit(getCurrentUser()?.copy(userEntity = user))
                isSignedIn(true)
                getTodoList()
            } else {
                isSignedIn(false)
            }
        }
    }

    fun signOut(signedOut: () -> Unit) {
        viewModelScope.launch {
            localRepository.clearUserDb()
            signedOut.invoke()
        }
    }

    fun getTodoList() {
        viewModelScope.launch {
            _userState.emit(
                getCurrentUser()?.copy(
                    todoList = listOf(),
                    isProgressBarVisible = true
                )
            )
            when (val list =
                remoteRepository.getTodoList(getCurrentUser()!!.userEntity!!.userId!!)) {
                is Failure -> _userState.emit(
                    getCurrentUser()?.copy(
                        todoList = null,
                        isProgressBarVisible = false
                    )
                )

                is Success -> _userState.emit(
                    getCurrentUser()?.copy(
                        todoList = list.body,
                        isProgressBarVisible = false
                    )
                )
            }
        }
    }
}