package com.example.fakestoreapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.fakestoreapi.databinding.LayoutProductBinding
import com.example.fakestoreapi.models.ProductResponse
import com.example.fakestoreapi.models.ProductItems

class ProductsAdapter:RecyclerView.Adapter<ProductsAdapter.CategoryItemsViewHolder>() {

    var categoryItemsList: ProductResponse = ProductResponse()
    var onItemClick:((categoryName:String) -> Unit)? = null
    class CategoryItemsViewHolder(val binding: LayoutProductBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(category:ProductItems?){
           binding.apply {
               productIv.load(category?.image) {
                   crossfade(true)
                   //placeholder(R.drawable.image)
                   transformations(RoundedCornersTransformation(10f))
               }
               titleTv.text = category?.title
               priceTv.text = category?.price.toString()
           }
        }
    }



    fun submitList(data: ProductResponse) {
        categoryItemsList.clear()
        categoryItemsList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemsViewHolder {
        return CategoryItemsViewHolder(LayoutProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return categoryItemsList.size
    }

    override fun onBindViewHolder(holder: CategoryItemsViewHolder, position: Int) {
        holder.bind(categoryItemsList[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(categoryItemsList[position].category)
        }
    }
}