package ru.netology.kotlinandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.kotlinandroid.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "111",
            publisher = "21 мая в 18:34",
            likes = 1099,
            views = 20,
            shares = 990,
            likedByMe = false
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "333 -> https://netology.ru/profile/program/and-58/lessons/411686/lesson_items/2223559",
            publisher = "21 мая в 18:35",
            likes = 99,
            views = 20,
            shares = 80,
            likedByMe = false
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "222",
            publisher = "21 мая в 18:35",
            likes = 99,
            views = 20,
            shares = 80,
            likedByMe = false
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "444",
            publisher = "21 мая в 18:35",
            likes = 99,
            views = 20,
            shares = 80,
            likedByMe = false
        ),
    ).reversed()
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun like(id: Long) {
        posts = posts.map {
            if (it.id != id) it else (it.copy(
                likedByMe = !it.likedByMe,
                likes = it.likes + if (it.likedByMe) -1 else 1
            ))
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                sharedByMe = !it.sharedByMe,
                shares = it.shares + if (it.sharedByMe) -10 else 10
            )
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = nextId++)) + posts
        } else posts = posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        data.value = posts
    }
}
