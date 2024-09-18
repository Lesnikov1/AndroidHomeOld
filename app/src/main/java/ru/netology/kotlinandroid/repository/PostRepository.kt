package ru.netology.kotlinandroid.repository

import androidx.lifecycle.LiveData
import ru.netology.kotlinandroid.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}