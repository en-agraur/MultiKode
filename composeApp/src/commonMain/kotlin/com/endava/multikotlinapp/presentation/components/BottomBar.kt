package com.endava.multikotlinapp.presentation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.endava.multikotlinapp.presentation.navigation.topLevelRoutes
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onItemClicked: (Any) -> Unit
) {

    BottomAppBar(modifier = modifier) {
        topLevelRoutes.onEach { topLevelRoute ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) topLevelRoute.iconFilled else topLevelRoute.icon,
                        contentDescription = stringResource(topLevelRoute.name)
                    )
                },
                onClick = { onItemClicked(topLevelRoute.route) },
                label = {
                    Text(
                        text = stringResource(topLevelRoute.name),
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                alwaysShowLabel = isSelected,
                selected = isSelected,
            )
        }
    }
}