package ru.netology.kotlinandroid.viewmodel

import activity.MainActivity
import androidx.lifecycle.ViewModel
import ru.netology.kotlinandroid.repository.PostRepository
import ru.netology.kotlinandroid.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()


}