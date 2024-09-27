package ru.netology.kotlinandroid.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.kotlinandroid.repository.PostRepository
import ru.netology.kotlinandroid.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)

}