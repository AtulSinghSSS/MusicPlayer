package com.example.firebaseproject.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(app: Application, private val repository: AppRepository): AndroidViewModel(app) {

   fun insertData(model: DataModel?) = viewModelScope.launch(Dispatchers.Main) {
        repository.insertData(model)
    }

}