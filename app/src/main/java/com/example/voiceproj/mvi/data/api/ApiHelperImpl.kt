package com.example.voiceproj.mvi.data.api

import com.example.voiceproj.mvi.data.model.User


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}