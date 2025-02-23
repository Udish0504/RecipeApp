package com.example.recipeapp2.Adapters.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp2.Adapters.repository.productService
import com.example.recipeapp2.Adapters.repository.recipeService
import com.example.recipeapp2.Entities.Category
import com.example.recipeapp2.Entities.Product
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel(){

    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState : State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    @SuppressLint("CheckResult")
    private fun fetchCategories(){
        viewModelScope.launch {
            try {

                val singleCategories = Single.fromCallable{ runBlocking {  recipeService.getCategories() } }.subscribeOn(Schedulers.io())
                val singleProducts = Single.fromCallable{ runBlocking { productService.getProducts() } }.subscribeOn(Schedulers.io())

                singleCategories.zipWith(singleProducts){
                    categoriesResponse, productResponse -> Pair(categoriesResponse,productResponse)
                }.subscribe(
                    {result->val (categories,products) = result
                        Log.d("Categories", "fetchCategories: ${categories.categories}")
                        Log.d("Products", "fetchCategories: $products")

                        _categoryState.value = _categoryState.value.copy(
                            categories = categories.categories,
                            products = products,
                            loading = false,
                            error = null
                        )
                    },{
                        error -> Log.e("API_ERROR","ERROR : ${error.message}")
                    }


                )

            }catch (e: Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error Fetching Categories ${e.message}"
                )
            }
        }
    }

    fun toggleData() {
        _categoryState.value = _categoryState.value.copy(toggleData = !categoryState.value.toggleData)
    }


    data class RecipeState(
        val loading : Boolean = true,
        val categories: List<Category> = emptyList(),
        val products:List<Product> = emptyList(),
        val error: String? = null,
        var toggleData:Boolean = true
    )
}