package com.endava.multikotlinapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.endava.multikotlinapp.presentation.screens.details.DetailsScreen
import com.endava.multikotlinapp.presentation.screens.list.ListScreen
import kotlinx.serialization.Serializable
import multikotlinapp.composeapp.generated.resources.Res
import multikotlinapp.composeapp.generated.resources.bookmarks_label
import multikotlinapp.composeapp.generated.resources.headlines_label
import multikotlinapp.composeapp.generated.resources.list_label
import org.jetbrains.compose.resources.StringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Headlines,
        modifier = modifier
    ) {
        composable<Headlines> {
            ListScreen(
                viewModel = koinViewModel(),
                onNavToDetails = { url, title -> navController.navigate(route = Details(url = url, title)) }
            )
        }

        composable<Details> { navBackStackEntry: NavBackStackEntry ->
            val details = navBackStackEntry.toRoute<Details>()
            DetailsScreen(url = details.url)
        }

        composable<Search> {

        }

        composable<Bookmarks> {

        }
    }
}

data class TopLevelRoute<T : Any>(val name: StringResource, val route: T, val icon: ImageVector, val iconFilled: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute(Res.string.headlines_label, Headlines, Icons.AutoMirrored.Outlined.List, Icons.AutoMirrored.Filled.List),
    TopLevelRoute(Res.string.list_label, Search, Icons.Outlined.Search, Icons.Filled.Search),
    TopLevelRoute(Res.string.bookmarks_label, Bookmarks, Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
)


@Serializable
object Search

@Serializable
object Bookmarks

@Serializable
object Headlines

@Serializable
data class Details(val url: String, val title: String)