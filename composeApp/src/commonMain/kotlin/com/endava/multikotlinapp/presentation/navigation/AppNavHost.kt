package com.endava.multikotlinapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.endava.multikotlinapp.presentation.screens.list.ListScreen
import kotlinx.serialization.Serializable
import multikotlinapp.composeapp.generated.resources.Res
import multikotlinapp.composeapp.generated.resources.bookmarks_label
import multikotlinapp.composeapp.generated.resources.headlines_label
import multikotlinapp.composeapp.generated.resources.list_label
import org.jetbrains.compose.resources.StringResource

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController, startDestination = List,
        modifier = modifier
    ) {
        composable<List> {
            ListScreen(
                onItemClicked = {

                }
            )
        }

        composable<Search> {

        }

        composable<Details> {

        }

        composable<Headlines> {

        }

        composable<Bookmarks> {

        }
    }
}

data class TopLevelRoute<T : Any>(val name: StringResource, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute(Res.string.list_label, List, Icons.Default.Home),
    TopLevelRoute(Res.string.headlines_label, Headlines, Icons.AutoMirrored.Default.List),
    TopLevelRoute(Res.string.bookmarks_label, Bookmarks, Icons.Outlined.FavoriteBorder),
)

@Serializable
object List

@Serializable
object Search

@Serializable
object Details

@Serializable
object Bookmarks

@Serializable
object Headlines