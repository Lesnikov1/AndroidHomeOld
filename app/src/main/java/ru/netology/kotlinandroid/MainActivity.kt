package ru.netology.kotlinandroid

import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import ru.netology.kotlinandroid.databinding.ActivityMainBinding
import ru.netology.kotlinandroid.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//         val constraintLayout: ConstraintLayout = findViewById(R.id.main)
//        constraintLayout.setOnClickListener {
//            Toast.makeText(this, "ConstraintLayout был нажат", Toast.LENGTH_SHORT).show()
//        }

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            publisher = "21 мая в 18:36",
            likes = 999,
            views = 20,
            shares = 990,
            likedByMe = false,
        )

        fun formatLikesCount(likesCount: Int): String {
            return when {
                likesCount >= 1_000_000 -> "${String.format("%.1f", likesCount / 1_000_000.0)}M"
                likesCount >= 1_000 -> "${likesCount / 1000}K"
                else -> likesCount.toString()
            }
        }

        with(binding) {
            author.text = post.author
            content.text = post.content
            //Linkify.addLinks(content, Linkify.WEB_URLS)
            publisher.text = post.publisher
            views.text = post.views.toString()
            likes.text = post.likes.toString()
            shares.text = post.shares.toString()

            root.setOnClickListener {
                Log.d("я", "ConstraintLayout был нажат")
            }

            share.setOnClickListener {
                post.sharedByMe = !post.sharedByMe
                post.shares += if (post.sharedByMe) 10 else -10
                shares.text = formatLikesCount(post.shares)
            }

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                post.likes += if (post.likedByMe) 1 else -1
                likes.text = formatLikesCount(post.likes)

                like.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_liked
                    } else {
                        R.drawable.ic_like
                    }
                )

            }
        }

    }
}