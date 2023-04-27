package com.example.fakestoreapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.adapters.CategoriesAdapter
import com.example.fakestoreapi.dataSource.repositroires.DataRepository
import com.example.fakestoreapi.databinding.ActivityMainBinding
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.viewModels.CategoriesProviderFactory
import com.example.fakestoreapi.viewModels.CategoriesViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CategoriesViewModel
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:CategoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataRepo = DataRepository(MyApplication.fakeStoreAPI)
        viewModel = ViewModelProvider(this,CategoriesProviderFactory(dataRepo)).get(CategoriesViewModel::class.java)

        adapter = CategoriesAdapter()
        adapter.onItemClick ={
            val intent:Intent = Intent(this@MainActivity,ProductsActivity::class.java)
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