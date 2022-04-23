package dev.matyaqubov.android_mvvm.networking


import dev.matyaqubov.android_mvvm.networking.service.PostService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private val IS_TESTER = true
    private val SERVER_DEVELOPMENT = "https://6260f1b3f429c20deb981149.mockapi.io/"
    private val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }
    val postService: PostService = retrofit.create(PostService::class.java)
}