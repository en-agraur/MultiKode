package com.endava.multikotlinapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.endava.multikotlinapp.presentation.navigation.AppNavHost
import com.endava.multikotlinapp.presentation.navigation.topLevelRoutes
import com.endava.multikotlinapp.presentation.theme.AppTheme
import com.endava.multikotlinapp.presentation.utils.contentModifier
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun KmmApp() {
    AppTheme {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { Toolbar(modifier = Modifier.fillMaxWidth()) },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomAppBar {
                    topLevelRoutes.onEach { topLevelRoute ->
                        NavigationBarItem(
                            icon = { Icon(imageVector = topLevelRoute.icon, contentDescription = stringResource(topLevelRoute.name)) },
                            onClick = {
                                navController.navigate(topLevelRoute.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(stringResource(topLevelRoute.name))
                            },
                            alwaysShowLabel = true,
                            selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true
                        )
                    }
                }
            }
        ) {
            AppNavHost(
                modifier = Modifier.contentModifier()
                    .padding(it),
                navController = navController
            )
        }
    }
}