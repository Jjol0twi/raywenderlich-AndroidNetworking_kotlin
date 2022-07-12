package com.example.androidnetworking.api

import com.example.androidnetworking.data.RepoResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
    private val service: GithubService

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    init {  //init으로 넣고 retrofit은 val로 지정 service로 create
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //gson converter로 회수
            .build()
        service = retrofit.create(GithubService::class.java)    //githubservice interface 호출
    }

    fun getRepositories(callback: Callback<RepoResult>) {
        service.searchRepositories().enqueue(callback)
    }
}