A modern Android application for browsing categories and products with interactive UI built using Jetpack Compose.
Users can navigate between categories (recipes and products) with click of a button and can navigate to their detail page by clicking on it.

Features Implemented
Recipe Listing – Displays a list of recipes fetched from an API
Product Listing – Shows products fetched from an API
Recipe Details – Displays Recipe image, title and description
Product Details – Displays product image, price, rating, and description
Navigation – User can navigate between listing page and details page by clicking on it or between the categories by click of a button.
ViewModel & State Management – Uses ViewModel to handle data efficiently
LazyColumn Optimization – Smooth scrolling with Jetpack Compose’s LazyColumn
Error Handling – Displays messages for empty categories or network failures


Tech Stack
Kotlin – Primary language
Jetpack Compose – UI toolkit for declarative UI
ViewModel – State management
Retrofit – API calls for fetching categories and products
Dagger Hilt - Dependency Injection
MVVM - Architecture

Challenges Faced
Listing the different categories on click of a button
Using zip function to call two APIs simultaenously
