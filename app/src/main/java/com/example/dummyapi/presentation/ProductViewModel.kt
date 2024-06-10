package com.example.dummyapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapi.data.ProductRepository
import com.example.dummyapi.data.Result
import com.example.dummyapi.products.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _EmptyBanner = Channel<Boolean>()
    val EmptyBanner = _EmptyBanner.receiveAsFlow()

    init {
        viewModelScope.launch {
            productRepository.getProductItems().collectLatest { result ->
                when (result) {
                    is Result.Failed -> {
                        _EmptyBanner.send(true)
                    }

                    is Result.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }


    }
}