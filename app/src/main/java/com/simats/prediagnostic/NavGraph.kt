package com.simats.prediagnostic

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(onGetStartedClicked = { navController.navigate("onboarding") })
        }
        composable("onboarding") {
            OnboardingScreen(onContinueClicked = { navController.navigate("roleSelection") })
        }
        composable("roleSelection") {
            RoleSelectionScreen(
                onPatientClicked = { navController.navigate("patientLogin") },
                onDoctorClicked = { navController.navigate("doctorLogin") }
            )
        }
        composable("patientLogin") {
            LoginScreen(
                onLoginClicked = { navController.navigate("home") },
                onSignUpClicked = { navController.navigate("patientSignUp") }
            )
        }
        composable("doctorLogin") {
            DoctorLoginScreen(
                onLoginClicked = { navController.navigate("doctorHome") },
                onSignUpClicked = { navController.navigate("doctorSignUp") }
            )
        }
        composable("patientSignUp") {
            PatientSignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onCreateAccountClicked = { navController.navigate("verifyPhone/patient") }
            )
        }
        composable("doctorSignUp") {
            DoctorSignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onCreateAccountClicked = { navController.navigate("verifyPhone/doctor") }
            )
        }
        composable(
            "verifyPhone/{userType}",
            arguments = listOf(navArgument("userType") { type = NavType.StringType })
        ) { backStackEntry ->
            val userType = backStackEntry.arguments?.getString("userType")
            VerifyPhoneScreen(
                onNavigateBack = { navController.popBackStack() },
                onVerifyClicked = { navController.navigate("profileSetup/$userType") }
            )
        }
        composable(
            "profileSetup/{userType}",
            arguments = listOf(navArgument("userType") { type = NavType.StringType })
        ) { backStackEntry ->
            val userType = backStackEntry.arguments?.getString("userType")
            ProfileSetupScreen(
                onNavigateBack = { navController.popBackStack() },
                onContinueClicked = {
                    if (userType == "doctor") {
                        navController.navigate("doctorProfessionalDetails")
                    } else {
                        navController.navigate("medicalHistory")
                    }
                }
            )
        }
        composable("medicalHistory") {
            MedicalHistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onContinueClicked = { navController.navigate("lifestyleDetails") }
            )
        }
        composable("lifestyleDetails") {
            LifestyleDetailsScreen(
                onNavigateBack = { navController.popBackStack() },
                onContinueClicked = { navController.navigate("privacyAndConsent") }
            )
        }
        composable("doctorProfessionalDetails") {
            DoctorProfessionalDetailsScreen(
                onContinueClicked = { navController.navigate("privacyAndConsent") }
            )
        }
        composable("privacyAndConsent") {
            PrivacyAndConsentScreen(
                onNavigateBack = { navController.popBackStack() },
                onCompleteSetupClicked = { navController.navigate("allSet") }
            )
        }
        composable("allSet") {
            AllSetScreen(
                onContinueToHomeClicked = { navController.navigate("home") }
            )
        }
        composable("home") {
            HomeScreen()
        }
        composable("doctorHome") {
            DoctorHomeScreen()
        }
    }
}
