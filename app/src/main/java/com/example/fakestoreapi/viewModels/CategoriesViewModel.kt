package com.example.fakestoreapi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.dataSource.repositroires.DataRepository
import com.example.fakestoreapi.models.Categories
import com.example.fakestoreapi.models.ProductResponse
import com.example.fakestoreapi.models.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(val repo:DataRepository) :ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCategories()
        }
    }

    val categories : LiveData<Resources<Categories>> get() = repo.categoriesList



    fun getCategoryItems(categroyName:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCategoryItems(categroyName)
        }

    }

    val categoryItems:LiveData<Resources<ProductResponse>> get() = repo.categoryItemsList

}