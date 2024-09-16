package ru.netology.kotlinandroid.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val publisher: String,
    var likes: Int = 0,
    var views: Int = 0,
    var shares: Int = 0,
    var likedByMe: Boolean = false,
    var sharedByMe: Boolean = false
)