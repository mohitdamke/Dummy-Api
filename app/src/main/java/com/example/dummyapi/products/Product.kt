package com.example.dummyapi.products

import android.media.Image

data class Product(
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val minimumOrderQuantity: Int,
    val price: String,
    val rating: Double,
    val returnPolicy: String,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val weight: Int
)