package dev.matyaqubov.android_mvvm.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import dev.matyaqubov.android_mvvm.R
import dev.matyaqubov.android_mvvm.model.Post
import dev.matyaqubov.android_mvvm.viewmodel.UpdatePostViewModel

class UpdatePostActivity : AppCompatActivity() {
    lateinit var viewModel: UpdatePostViewModel
    lateinit var et_title: EditText
    lateinit var et_body: EditText
    lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(UpdatePostViewModel::class.java)
        et_title = findViewById(R.id.et_title)
        et_body = findViewById(R.id.et_body)
        setData()
        val b_update = findViewById<Button>(R.id.btn_update).also {
            it.setOnClickListener {
                updatePost()
            }
        }

    }

    private fun updatePost() {
        post.title = et_title.text.toString()
        post.body = et_body.text.toString()
        viewModel.apiUpdatePost(post).observe(this, {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }

    private fun setData() {
        post = intent.getSerializableExtra("post") as Post
        et_title.setText(post.title)
        et_body.setText(post.body)
    }
}