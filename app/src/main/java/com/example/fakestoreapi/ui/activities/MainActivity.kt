package com.example.fakestoreapi.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.fakestoreapi.ui.adapters.CategoriesAdapter
import com.example.fakestoreapi.beGone
import com.example.fakestoreapi.beVisible
import com.example.fakestoreapi.databinding.ActivityMainBinding
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.ui.viewModels.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     private val  viewModel: CategoriesViewModel by viewModels()
    lateinit var binding:ActivityMainBinding
    @Inject lateinit var adapter: CategoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val dataRepo = DataRepository(MyApplication.fakeStoreAPI)
        *///viewModel = ViewModelProvider(this,).get(CategoriesViewModel::class.java)

/*        adapter = CategoriesAdapter()*/
        adapter.onItemClick ={
            val intent:Intent = Intent(this@MainActivity, ProductsActivity::class.java)
            intent.putExtra("categoryName",it)
            startActivity(intent)
        }
        binding.categoryRv.adapter = adapter
        viewModel.categories.observe(this) {
            when(it){
                is Resources.Loading ->{
                    binding.progressBar.beVisible()
                }
                is Resources.Success ->{
                    binding.progressBar.beGone()
                    adapter.submitList(it.data)
                    binding.categoryRv.adapter = adapter

                }
                is Resources.Error -> {
                    binding.progressBar.beGone()
                    Toast.makeText(this@MainActivity,"error = " + it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}