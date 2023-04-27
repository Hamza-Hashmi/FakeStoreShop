package com.example.fakestoreapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fakestoreapi.adapters.ProductsAdapter
import com.example.fakestoreapi.dataSource.repositroires.DataRepository
import com.example.fakestoreapi.databinding.ActivityProductsBinding
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.viewModels.CategoriesProviderFactory
import com.example.fakestoreapi.viewModels.CategoriesViewModel

class ProductsActivity : AppCompatActivity() {


    private lateinit var viewModel:CategoriesViewModel
    lateinit var adapter: ProductsAdapter
    lateinit var binding:ActivityProductsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dataRepo = DataRepository(MyApplication.fakeStoreAPI)

        viewModel = ViewModelProvider(this, CategoriesProviderFactory(dataRepo)).get(CategoriesViewModel::class.java)

        adapter = ProductsAdapter()

        val categoryName = intent.getStringExtra("categoryName")

        viewModel.getCategoryItems(categoryName!!)

        viewModel.categoryItems.observe(this) {
            when(it){
                is Resources.Loading ->{

                    binding.shimmerLayout.beVisible()
                }
                is Resources.Success ->{
                    binding.shimmerLayout.beGone()
                    it.data?.let { it1 -> adapter.submitList(it1) }
                    binding.itemsRv.adapter = adapter
                }
                is Resources.Error ->{
                    binding.shimmerLayout.beGone()
                    Toast.makeText(this@ProductsActivity, "error ${it.errorMessage.toString()}", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
}