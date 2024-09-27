package ru.netology.kotlinandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.kotlinandroid.R
import ru.netology.kotlinandroid.databinding.CardPostBinding
import ru.netology.kotlinandroid.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostAdapter(
    private val onShareListener: OnShareListener,
    private val onLikeListener: OnLikeListener
) : RecyclerView.Adapter<PostViewHolder>() {

    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onShareListener, onLikeListener)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onShareListener: OnShareListener,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {

    private fun formatValue(value: Int): String {
        return when {
            value >= 1_000_000 -> "${String.format("%.1f", value / 1_000_000.0)}M"
            value >= 10_000 -> "${value / 1000}K"
            value >= 1_100 -> "${String.format("%.1f", value / 1000.0)}K"
            else -> value.toString()
        }
    }

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            content.text = post.content
            publisher.text = post.publisher
            likes.text = formatValue(post.likes)
            like.setImageResource(
                if (post.likedByMe) {
                    R.drawable.ic_liked
                } else {
                    R.drawable.ic_like
                }
            )
            shares.text = formatValue(post.shares)
            views.text = post.views.toString()

            like.setOnClickListener {
                onLikeListener(post)
            }

            share.setOnClickListener {
                onShareListener(post)
            }
        }

    }
}


