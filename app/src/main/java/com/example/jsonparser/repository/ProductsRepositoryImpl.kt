package com.example.jsonparser.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.jsonparser.api.Api
import com.example.jsonparser.api.Result
import com.example.jsonparser.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class ProductsRepositoryImpl (
    private val api: Api
):ProductsRepository{
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getProductList(): Flow<com.example.jsonparser.api.Result<List<Product>>> {
        return flow{
        val productsFromApi=try {
            api.getProductsList()
        }catch (e: IOException){
            e.printStackTrace()
            emit(com.example.jsonparser.api.Result.Error(message = "Error loading Products"))
            return@flow
        }catch (e: HttpException){
            e.printStackTrace()
            emit(com.example.jsonparser.api.Result.Error(message = "Error loading Products"))
            return@flow
        }
            emit(Result.Success(productsFromApi.products))
        }

    }
}