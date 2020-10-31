package com.surelabs.indonesia.koreancornersreaders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surelabs.e.jsoupbotapps.model.ResponsePost
import com.surelabs.indonesia.koreancornersreaders.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {
    var res = MutableLiveData<ResponsePost>()
    var error = MutableLiveData<String>()

    private val api = NetworkModule.getService()


    fun getTransaksi(q: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = api.getPost(q)
                res.postValue(result)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        error.postValue(throwable.message)
                    }
                    is HttpException -> {
                        error.postValue(throwable.message)
                    }
                    else -> {
                        error.postValue("Unknown Error")
                    }
                }
            }
        }
    }
}