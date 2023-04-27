package com.example.fakestoreapi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.dataSource.repositroires.DataRepository

class CategoriesProviderFactory(val dataRepo: DataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesViewModel(dataRepo) as T
    }


}