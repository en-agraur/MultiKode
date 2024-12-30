package com.endava.multikotlinapp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.endava.multikotlinapp.presentation.components.BottomBar
import com.endava.multikotlinapp.presentation.components.Toolbar
import com.endava.multikotlinapp.presentation.navigation.AppNavHost
import com.endava.multikotlinapp.presentation.navigation.topLevelRoutes
import com.endava.multikotlinapp.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun KmmApp() {
    AppTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination
        var isSecondaryScreen by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(currentDestination) {
            isSecondaryScreen = topLevelRoutes.none { currentDestination?.hasRoute(it.route::class) == true }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AnimatedVisibility(
                    visible = isSecondaryScreen,
                    enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
                ) {
                    Toolbar(
                        modifier = Modifier.fillMaxWidth(),
                        hasBackAction = true,
                        onBack = { navController.popBackStack() },
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {

                AnimatedVisibility(
                    visible = !isSecondaryScreen,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                ) {
                    BottomBar(
                        modifier = Modifier.fillMaxWidth(),
                        onItemClicked = {
                            navController.navigate(it) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        currentDestination = navBackStackEntry?.destination
                    )
                }
            }
        ) {
            AppNavHost(
                modifier = Modifier.padding(it),
                navController = navController
            )
        }
    }
}