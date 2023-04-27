package com.example.fakestoreapi.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.dataSource.repositroires.RegistrationRepo
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.models.SignUpResponse
import com.example.fakestoreapi.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(val repo:RegistrationRepo):ViewModel() {
    private val _signupResopnse:MutableLiveData<Resources<SignUpResponse>?> = MutableLiveData()

    val signUpResponse:LiveData<Resources<SignUpResponse>?> get() = _signupResopnse

    fun createUser(user: User){

        viewModelScope.launch(Dispatchers.Main) {

            _signupResopnse.postValue(Resources.Loading())
            try {
                val response = repo.createUser(user)
                if (response?.body()!= null){
                    _signupResopnse.postValue(Resources.Success(response.body()))
                }
            }catch (e:Error){
                 _signupResopnse.postValue(Resources.Error(e.message.toString()))
            }

        }
    }


}