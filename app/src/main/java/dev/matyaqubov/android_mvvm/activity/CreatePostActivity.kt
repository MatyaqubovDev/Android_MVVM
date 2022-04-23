package dev.matyaqubov.android_mvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.matyaqubov.android_mvvm.R
import dev.matyaqubov.android_mvvm.model.Post
import dev.matyaqubov.android_mvvm.viewmodel.CreatePostViewModel

class CreatePostActivity : AppCompatActivity() {
    lateinit var viewModel: CreatePostViewModel
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(CreatePostViewModel::class.java)
        et_body = findViewById(R.id.et_body)
        et_title = findViewById(R.id.et_title)
        var b_create = findViewById<Button>(R.id.btn_post).also {
            it.setOnClickListener {
                createPost()
            }
        }
    }

    private fun createPost() {
        val title = et_title.text.toString()
        val body = et_body.text.toString()
        val post = Post(1, 2, title, body)
        viewModel.apiCreatePost(post).observe(this, {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        })
    }
}