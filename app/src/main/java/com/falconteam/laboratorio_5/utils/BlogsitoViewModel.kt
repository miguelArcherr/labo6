package com.falconteam.laboratorio_5.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.laboratorio_5.data.database.InitDatabase
import com.falconteam.laboratorio_5.data.database.daos.PostDao
import com.falconteam.laboratorio_5.data.database.entities.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BlogsitoViewModel() : ViewModel() {
    private val db = InitDatabase.database
    private val _listPosts = MutableStateFlow<List<PostEntity>>(emptyList())
    val listPosts = _listPosts.asStateFlow()
    val showModal = mutableStateOf(false)
    val newPostTitle = mutableStateOf("")
    val newPostDescription = mutableStateOf("")

    init {
        getPosts()
    }
    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = db.postDao().observeAll().firstOrNull()

            // Verificamos que no este vacio
            if (result?.isEmpty() == true) {
                return@launch
            }

            db.postDao().observeAll().collect { listPosts ->
                _listPosts.value = listPosts
            }
        }
    }
    fun showModal() {
        showModal.value = !showModal.value
    }

    private fun cleanFields() {
        newPostDescription.value = ""
        newPostTitle.value = ""
    }

    fun addNewPost() {
        if (newPostDescription.value.isEmpty()) {
            return
        }

        if (newPostTitle.value.isEmpty()) {
            return
        }

        val newPost = PostEntity(
            title = newPostTitle.value,
            description = newPostDescription.value,
            author = "EnSeMirro"
        )

        viewModelScope.launch(Dispatchers.IO) {
            db.postDao().insertPost(newPost)
            getPosts()
        }

        cleanFields()

    }

    fun updatePost(title: String, description: String, id: String) {
        viewModelScope.launch {
            db.postDao().updateSelected(title, description, id)
        }
    }

    fun deletePost(postId: String) {
        viewModelScope.launch {
            db.postDao().deletePostById(postId)
        }
    }

}