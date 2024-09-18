package ru.netology.kotlinandroid.repository

import androidx.lifecycle.MutableLiveData
import ru.netology.kotlinandroid.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        publisher = "21 мая в 18:36",
        likes = 999,
        views = 20,
        shares = 990,
        likedByMe = false,
    )

    private val data = MutableLiveData(post)

    override fun get() = data

    override fun like() {
        post = post.copy(
            likedByMe = !post.likedByMe,
            likes = post.likes + if (post.likedByMe) -1 else 1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(
            sharedByMe = !post.sharedByMe,
            shares = post.shares + if (post.sharedByMe) -10 else 10
        )
        data.value = post
    }
}