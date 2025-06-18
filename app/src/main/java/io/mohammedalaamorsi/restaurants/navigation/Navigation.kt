package io.mohammedalaamorsi.restaurants.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.mohammedalaamorsi.restaurants.presentation.restaurant_details.RestaurantsDetailsScreen
import io.mohammedalaamorsi.restaurants.presentation.restaurant_details.RestaurantDetailsViewModel
import io.mohammedalaamorsi.restaurants.presentation.restaurants_list.RestaurantsListScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun RootNavHost(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screens.RestaurantsList,
        modifier = modifier,
    ) {
        composable<Screens.RestaurantsList> {
            RestaurantsListScreen(
                onRestaurantClicked = { id ->
                    navHostController.navigate(
                        Screens.RestaurantDetail(id)
                    )
                }
            )
        }
        composable<Screens.RestaurantDetail> { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id", "")

            val restaurantDetailsViewModel = koinViewModel<RestaurantDetailsViewModel> {
                parametersOf(id)
            }
            RestaurantsDetailsScreen(
                viewModel = restaurantDetailsViewModel,
                onNavigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
