package com.example.recipeapp2.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeapp2.Adapters.viewModel.MainViewModel
import com.example.recipeapp2.Entities.Category


@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeScreen(modifier: Modifier = Modifier, viewState: MainViewModel.RecipeState, navigateToDetail : (Category) -> Unit){


        Box(modifier = Modifier.fillMaxSize()) {
            when {
                viewState.loading -> {
                    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                        items(8) { ShimmerCategoryItem() }
                    }
                }

                viewState.error != null -> {
                    Text("ERROR OCCURED")
                }

                else -> {
                    CategoryScreen(categories = viewState.categories, navigateToDetail)
                }
            }
        }
}

@Composable
fun CategoryScreen(categories:List<Category>, navigateToDetail : (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {

        items(categories){
            category -> CategoryItem(category = category, navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigateToDetail : (Category) -> Unit){
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp).clickable { navigateToDetail(category) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardColors(Color.White,Color.Black,Color.White,Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    model = category.strCategoryThumb,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}






