package com.example.voiceproj.mvi.data.api

import com.example.voiceproj.mvi.data.model.User
import retrofit2.http.GET

interface ApiService {

   @GET("users")
   suspend fun getUsers(): List<User>
}