package com.example.recipeapp2.Adapters.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp2.Adapters.viewModel.MainViewModel
import com.example.recipeapp2.Entities.Category
import com.example.recipeapp2.Entities.Product
import com.example.recipeapp2.Entities.Rating
import com.example.recipeapp2.Entities.Screen
import com.example.recipeapp2.ui.CategoryDetailScreen
import com.example.recipeapp2.ui.ProductDetailScreen
import com.example.recipeapp2.ui.RecipeScreen
import com.example.recipeapp2.ui.ShopScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val recipeViewModel: MainViewModel = hiltViewModel()
    val viewState by recipeViewModel.categoryState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My App") },
                actions = {
                    Button(onClick = { recipeViewModel.toggleData() }) {
                        Text(
                            text = if (viewState.toggleData) "Products" else "Recipes"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (viewState.toggleData) {
                RecipeApp(navController = navController)
            } else {
                ProductApp(navController = navController)
            }
        }
    }
}

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
            composable(route = Screen.RecipeScreen.route){
                RecipeScreen(viewState = viewState,navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                    navController.navigate(Screen.DetailScreen.route)
                })
            }
            composable(route = Screen.DetailScreen.route){
                val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?: Category("","","","")
                CategoryDetailScreen(category = category)
            }
    }
}

@Composable
fun ProductApp(navController: NavHostController){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.ProductScreen.route){
        composable(route = Screen.ProductScreen.route){
            ShopScreen(viewState = viewState,navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.ProductDetailScreen.route)
            })
        }
        composable(route = Screen.ProductDetailScreen.route){
            val product = navController.previousBackStackEntry?.savedStateHandle?.get<Product>("cat")?: Product(
                "", "",
                price = 0.0,
                description = "",
                category = "",
                image = "",
                rating = Rating(0.0,0),
            )
            ProductDetailScreen(product = product)
        }
    }
}

