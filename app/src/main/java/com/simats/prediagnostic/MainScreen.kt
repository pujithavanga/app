package com.simats.prediagnostic

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    Screen.Home,
                    Screen.Screening,
                    Screen.History,
                    Screen.Profile
                )

                items.forEach { screen ->
                    screen.icon?.let {
                        NavigationBarItem(
                            icon = { Icon(it, contentDescription = screen.route) },
                            label = { Text(screen.route) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { 
                HomeScreen(
                    onHealthScreeningClicked = { navController.navigate(Screen.HealthScreening.route) },
                    onMedicalScreeningClicked = { navController.navigate(Screen.MedicalScreening.route) },
                    onAiHealthChatClicked = { navController.navigate(Screen.AiHealthAssistant.route) },
                    onDoctorChatClicked = { navController.navigate(Screen.Messages.route) },
                    onRecentActivityClicked = { reportName -> navController.navigate("reportDetails/$reportName") },
                    onConsultDoctorClicked = { navController.navigate(Screen.ConsultDoctor.route) },
                    onNotificationsClicked = { navController.navigate(Screen.Notifications.route) }
                )
            }
            composable(Screen.Screening.route) { 
                HealthScreeningScreen(
                    onNavigateBack = { navController.popBackStack() }, 
                    onCategoryClicked = { navController.navigate(Screen.UploadMedicalReport.route) })
            }
            composable(Screen.History.route) { 
                ScreeningHistoryScreen(
                    onScreeningClicked = { reportName -> navController.navigate("reportDetails/$reportName") }
                ) 
            }
            composable(Screen.Profile.route) { 
                ProfileScreen(
                    onEditProfileClicked = { navController.navigate(Screen.EditProfile.route) }, 
                    onPrivacyAndDataClicked = { navController.navigate(Screen.PrivacyAndData.route) }, 
                    onSettingsClicked = { navController.navigate(Screen.Settings.route) }, 
                    onLogoutClicked = { navController.navigate(Screen.Welcome.route) }
                ) 
            }
        }
    }
}
