package ru.netology.kotlinandroid.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.kotlinandroid.R
import ru.netology.kotlinandroid.adapter.OnInteractionListener
import ru.netology.kotlinandroid.adapter.PostAdapter
import ru.netology.kotlinandroid.databinding.ActivityMainBinding
import ru.netology.kotlinandroid.dto.Post
import ru.netology.kotlinandroid.util.AndroidUtils
import ru.netology.kotlinandroid.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {

            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onClearEdit() {
                viewModel.clearEdit()
            }

        }
        )

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val nwePost = posts.size > adapter.currentList.size && adapter.currentList.isNotEmpty()

            adapter.submitList(posts) {
                if (nwePost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.viewGroup.visibility = View.VISIBLE
                binding.content.setText(it.content)
                binding.content.setSelection(binding.content.text.length)
                binding.contentEdit.text = it.content
                binding.content.requestFocus()
            }
        }

        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isBlank()) {
                Toast.makeText(this@MainActivity, R.string.error_empty_content, Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            viewModel.applyChangesAndSave(text)
            binding.viewGroup.visibility = View.GONE
            binding.content.setText("")
            binding.contentEdit.text = ""
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
        }

        binding.close.setOnClickListener {
            binding.viewGroup.visibility = View.GONE
            binding.content.setText("")
            binding.contentEdit.text = ""
            viewModel.clearEdit()
        }


    }
}
