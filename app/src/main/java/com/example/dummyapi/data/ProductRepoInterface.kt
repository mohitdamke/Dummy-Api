package com.example.dummyapi.data

import com.example.dummyapi.products.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepoInterface(private val api: Api) : ProductRepository {
    override suspend fun getProductItems(): Flow<Result<List<Product>>> {
        return flow {
            val itemsFromApi =
                try {
                    api.getProductItems()
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Result.Failed(message = "Failed to fetch data"))
                    return@flow
                }
            emit(Result.Success(itemsFromApi.products))
        }
    }

}