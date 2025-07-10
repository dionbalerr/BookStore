package com.example.ssbookstore

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun App() {
    // navController: map
    val navController = rememberNavController()

    //navHost: tour guide
    NavHost(navController = navController, startDestination = "login-screen") {
        navigationGraph(navController = navController)
    }
}

// navGraph: itinerary
fun NavGraphBuilder.navigationGraph(navController: NavController){
    // destinations: screens, actions: routes
    composable("login-screen"){
        LoginScreen(navController)
    }

    composable("listing-screen"){
        BookListing(navController)
    }

    composable("details-screen/{bookId}",
        // tells we are expecting string arg named bookId
        arguments = listOf(navArgument("bookId"){ type = NavType.StringType})
    ){ backStackEntry ->
        val bookId = backStackEntry.arguments?.getString("bookId")
        BookDetails(bookId = bookId, navController = navController)
    }

    composable("create-screen"){
        CreateBook(navController)
    }

    composable("edit-screen/{bookId}",
        arguments = listOf(navArgument("bookId"){ type = NavType.StringType})
    ){ backStackEntry ->
        val bookId = backStackEntry.arguments?.getString("bookId")
        EditDetails(bookId = bookId, navController = navController)
    }
}