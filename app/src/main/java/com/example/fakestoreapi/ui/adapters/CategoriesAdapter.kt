package com.example.fakestoreapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.LayoutCategoriesBinding

import com.example.fakestoreapi.models.Categories
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var categoryList:Categories? = Categories()
    var onItemClick:((categoryName:String) -> Unit)? = null
    class CategoriesViewHolder(val binding: LayoutCategoriesBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(category: String?, position: Int){
            binding.tvCategory.text = category
            when(position) {
                0 ->{
                    binding.categoryIv.setImageResource(R.drawable.electronics)
                }
                1->{
                    binding.categoryIv.setImageResource(R.drawable.jewellery)
                }
                2->{
                    binding.categoryIv.setImageResource(R.drawable.men)
                }
                3->{
                    binding.categoryIv.setImageResource(R.drawable.women)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(LayoutCategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return categoryList!!.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        holder.bind(categoryList?.get(position) , position)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(categoryList!!.get(position))
        }
    }

    fun submitList(data: Categories?) {
        categoryList?.clear()
        categoryList = data
    }


}