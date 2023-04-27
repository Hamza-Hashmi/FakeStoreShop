package com.example.fakestoreapi.models

data class ProductItems(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: ProductRating,
    val title: String
)