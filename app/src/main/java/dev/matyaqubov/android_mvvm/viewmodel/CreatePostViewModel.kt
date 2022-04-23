package dev.matyaqubov.android_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.matyaqubov.android_mvvm.model.Post

class CreatePostViewModel : ViewModel() {
    val createdPost = MutableLiveData<Post>()

    fun apiCreatePost(){

    }
}