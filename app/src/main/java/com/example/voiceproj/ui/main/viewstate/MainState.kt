package com.example.voiceproj.ui.main.viewstate

import com.example.voiceproj.mvi.data.model.User


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}