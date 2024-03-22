package com.example.jsonparser.repository

import com.example.jsonparser.api.Result
import com.example.jsonparser.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProductList():Flow<Result<List<Product>>>
}