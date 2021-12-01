package com.example.voiceproj.mvi.data.repository

import com.example.voiceproj.mvi.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}