package dev.matyaqubov.android_mvvm.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.matyaqubov.android_mvvm.R
import dev.matyaqubov.android_mvvm.adapter.PosterAdapter
import dev.matyaqubov.android_mvvm.model.Post
import dev.matyaqubov.android_mvvm.networking.RetrofitHttp
import dev.matyaqubov.android_mvvm.utils.SwipeHelper
import dev.matyaqubov.android_mvvm.utils.Utils
import dev.matyaqubov.android_mvvm.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PosterAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }


    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        viewModel.apiPostList().observe(this, {
            refreshAdapter(it)
        })

        val b_add = findViewById<FloatingActionButton>(R.id.f_button).also {
            it.setOnClickListener {
                callCreatePostActivity()
            }
        }

        SwipeHelper.setSwipeRightToLeft(this, recyclerView)

    }

    private fun callCreatePostActivity() {
        val intent = Intent(this, CreatePostActivity::class.java)
        createPostLauncher.launch(intent)
    }

    fun callUpdatePostActivity(post: Post) {
        val intent = Intent(this, UpdatePostActivity::class.java)
        intent.putExtra("post",post)
        updatePostLauncher.launch(intent)
    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        adapter = PosterAdapter(this, posters)
        recyclerView.setAdapter(adapter)
    }

    val createPostLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.apiPostList().observe(this, { posts ->
                    refreshAdapter(posts)
                })
            }
        }

    val updatePostLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.apiPostList().observe(this, { posts ->
                    refreshAdapter(posts)
                })
            }
        }
}