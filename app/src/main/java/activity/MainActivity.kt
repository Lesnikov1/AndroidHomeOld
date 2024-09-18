package activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.kotlinandroid.R
import ru.netology.kotlinandroid.databinding.ActivityMainBinding
import ru.netology.kotlinandroid.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    fun formatlikes(likes: Int): String {
        return when {
            likes >= 1_000_000 -> "${String.format("%.1f", likes / 1_000_000.0)}M"
            likes >= 1_000 -> "${likes / 1000}K"
            else -> likes.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                //Linkify.addLinks(content, Linkify.WEB_URLS)
                publisher.text = post.publisher
                likes.text = formatlikes(post.likes)
                like.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_liked
                    } else {
                        R.drawable.ic_like
                    }
                )
                shares.text = formatlikes(post.shares)
                views.text = post.views.toString()
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }

    }
}
//         val constraintLayout: ConstraintLayout = findViewById // для проверки
//        constraintLayout.setOnClickListener {
//            Toast.makeText(this, "ConstraintLayout был нажат", Toast.LENGTH_SHORT).show()
//        }