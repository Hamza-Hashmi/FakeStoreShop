package com.example.fakestoreapi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.dataSource.repositroires.RegistrationRepo

class RegistrationProviderFactory(val registrationRepo: RegistrationRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(registrationRepo) as T
    }
}