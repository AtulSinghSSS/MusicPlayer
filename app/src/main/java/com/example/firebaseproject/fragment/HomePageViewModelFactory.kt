package com.example.firebaseproject.fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproject.appRepository.AppRepository

class HomePageViewModelFactory(val app: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomePageViewModel(app,repository) as T
    }
}