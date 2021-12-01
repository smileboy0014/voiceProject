package com.example.voiceproj.mvi.data.api

import com.example.voiceproj.mvi.data.model.User

interface ApiHelper {

    suspend fun getUsers(): List<User>

}