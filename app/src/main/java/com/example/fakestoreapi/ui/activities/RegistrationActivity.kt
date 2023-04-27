package com.example.fakestoreapi.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.fakestoreapi.beGone
import com.example.fakestoreapi.beVisible
import com.example.fakestoreapi.databinding.ActivityRegistrationBinding
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.models.User
import com.example.fakestoreapi.startNewActivity
import com.example.fakestoreapi.utils.UserPreferences
import com.example.fakestoreapi.ui.viewModels.RegistrationViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegistrationBinding
    val registrationViewModel: RegistrationViewModel by viewModels()

    lateinit var dataStore:UserPreferences
    private var userId:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = UserPreferences(this@RegistrationActivity)


        binding.layoutRegistration.beGone()


        runBlocking {
            userId = async {  getUserId() }.await()

            delay(2000)
        }

        if (userId != null){
                startNewActivity(MainActivity::class.java)
        }
        else{
                binding.progrssBar.beGone()
                binding.layoutRegistration.beVisible()
        }

//        val registrationRepo = RegistrationRepo(fakeStoreAPI)

//        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        binding.apply {
            btnSignup.setOnClickListener {
               val userName =  edtUserName.text.toString()
                val password = edtUserName.text.toString()
                if (TextUtils.isEmpty(userName.trim())){
                    edtUserName.error = "Required"
                    return@setOnClickListener
                }
                else if (TextUtils.isEmpty(password.trim())){
                    edtPassword.error = "Required"
                }
                else{

                    val user = User(password,userName)

                    registrationViewModel.createUser(user)
                    registrationViewModel.signUpResponse.observe(this@RegistrationActivity) {

                    when(it){
                        is Resources.Loading -> {
                           // Toast.makeText(this@RegistrationActivity, "loadingcall", Toast.LENGTH_SHORT).show()
                            binding.progrssBar.beVisible()
                        }
                        is Resources.Success -> {
                            binding.progrssBar.beGone()
                            lifecycleScope.launch {
                                dataStore.saveUserId(it.data?.id!!)
                            }
                            startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                            //Toast.makeText(this@RegistrationActivity, "id  = " + it.data?.id.toString(), Toast.LENGTH_SHORT).show()

                        }
                        is Resources.Error -> {
                            binding.progrssBar.beGone()
                            Toast.makeText(this@RegistrationActivity, "id  = " + it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                        }
                        null -> {
                            binding.progrssBar.beGone()
                            Toast.makeText(this@RegistrationActivity, "null case called " , Toast.LENGTH_SHORT).show()

                        }
                    }
                    }
                }

            }
        }
    }

    private fun getUserId():String{
           return dataStore.getUserId.asLiveData().observe(this) {}.toString()
    }
}