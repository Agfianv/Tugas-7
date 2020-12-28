package com.example.underlords.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.underlords.data.model.ActionState
import com.example.underlords.data.model.News
import com.example.underlords.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repo: NewsRepository by lazy { NewsRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<News>>()
    val detailResp = MutableLiveData<News>()
    val searchResp = MutableLiveData<News>()

    val url = MutableLiveData("")
    val query = MutableLiveData("")

    fun listNews() {
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listNews()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailNews(url: String? = this.url.value) {
        url?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailNews(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false
            }
        }
    }

    fun searchNews(url: String? = this.url.value) {
        url?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailNews(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false
            }
        }
    }
}