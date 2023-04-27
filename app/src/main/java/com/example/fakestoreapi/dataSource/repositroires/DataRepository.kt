package com.example.fakestoreapi.dataSource.repositroires

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestoreapi.models.Categories
import com.example.fakestoreapi.models.ProductResponse
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.network.FakeStoreAPI
import javax.inject.Inject

class DataRepository @Inject constructor(val fakeStoreAPI: FakeStoreAPI) {

    private val _categoriesList = MutableLiveData<Resources<Categories>>()

    val categoriesList: LiveData<Resources<Categories>> get() = _categoriesList

    private val _productsList = MutableLiveData<Resources<ProductResponse>>()
    val productsList : LiveData<Resources<ProductResponse>> get() = _productsList

    suspend fun getCategories(){
        _categoriesList.postValue(Resources.Loading())
        val result = fakeStoreAPI.getCategories()

        try{

            if (result.body() !=null){
                _categoriesList.postValue(Resources.Success(result.body()))
            }

        }catch (e:Exception){
            _categoriesList.postValue(Resources.Error(e.message.toString()))
        }
    }

    suspend fun getCategoryItems(categoryName:String){
        _productsList.postValue(Resources.Loading())

        val result = fakeStoreAPI.getCategoryItems(categoryName)

        try{

            if (result?.body() !=null){
                _productsList.postValue(Resources.Success(result?.body()))
            }

        }catch (e:Exception){
            _productsList.postValue(Resources.Error(e.message.toString()))
        }
    }
}