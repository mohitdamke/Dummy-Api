package com.example.dummyapi.data

import com.example.dummyapi.products.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductItems() : Flow<Result<List<Product>>>
}