package dev.matyaqubov.android_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.matyaqubov.android_mvvm.model.Post
import dev.matyaqubov.android_mvvm.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostViewModel : ViewModel() {
    val createdPost = MutableLiveData<Post>()

    fun apiCreatePost(post: Post): LiveData<Post> {

        RetrofitHttp.postService.createPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                createdPost.value = response.body()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                createdPost.value = null
            }

        })
        return createdPost
    }
}