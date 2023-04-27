package com.example.fakestoreapi.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fakestoreapi.ui.adapters.ProductsAdapter
import com.example.fakestoreapi.beGone
import com.example.fakestoreapi.beVisible
import com.example.fakestoreapi.databinding.ActivityProductsBinding
import com.example.fakestoreapi.models.Resources
import com.example.fakestoreapi.ui.viewModels.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {


    val  viewModel: CategoriesViewModel by viewModels()
   @Inject
   lateinit var adapter: ProductsAdapter
    lateinit var binding:ActivityProductsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)

        setContentView(binding.root)

   //     val dataRepo = DataRepository(MyApplication.fakeStoreAPI)

//        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)

/*
        adapter = ProductsAdapter()
*/

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