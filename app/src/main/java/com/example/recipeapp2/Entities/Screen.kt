package com.example.recipeapp2.Entities

sealed class Screen(val route:String) {
    object RecipeScreen: Screen("recipescreen")
    object DetailScreen: Screen("detailscreen")
    object ProductScreen: Screen("productscreen")
    object  ProductDetailScreen: Screen("productdetailscreen")
}