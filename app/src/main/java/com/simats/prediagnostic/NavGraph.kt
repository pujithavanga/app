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
fun NavGraph(startDestination: String = Screen.Welcome.route) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val showNavBar = currentDestination?.route in listOf(Screen.Home.route, Screen.Screening.route, Screen.History.route, Screen.Profile.route)
            if (showNavBar) {
                NavigationBar {
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(onGetStartedClicked = { navController.navigate(Screen.Onboarding.route) })
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginClicked = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    },
                    onSignUpClicked = { navController.navigate(Screen.PatientSignUp.route) }
                )
            }
            composable(Screen.DoctorLogin.route) {
                DoctorLoginScreen(
                    onLoginClicked = {
                        navController.navigate(Screen.DoctorHome.route) {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    },
                    onSignUpClicked = { navController.navigate(Screen.DoctorSignUp.route) }
                )
            }
            composable(Screen.Onboarding.route) {
                OnboardingScreen(onContinueClicked = { navController.navigate(Screen.RoleSelection.route) })
            }
            composable(Screen.RoleSelection.route) {
                RoleSelectionScreen(
                    onPatientClicked = { navController.navigate(Screen.Login.route) },
                    onDoctorClicked = { navController.navigate(Screen.DoctorLogin.route) }
                )
            }
            composable(Screen.PatientSignUp.route) {
                PatientSignUpScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onCreateAccountClicked = { navController.navigate(Screen.VerifyPhone.route) }
                )
            }
            composable(Screen.DoctorSignUp.route) {
                DoctorSignUpScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onCreateAccountClicked = { navController.navigate(Screen.DoctorHome.route) }
                )
            }
            composable(Screen.VerifyPhone.route) {
                VerifyPhoneScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onVerifyClicked = { navController.navigate(Screen.ProfileSetup.route) }
                )
            }
            composable(Screen.ProfileSetup.route) {
                ProfileSetupScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onContinueClicked = { navController.navigate(Screen.MedicalHistory.route) }
                )
            }
            composable(Screen.MedicalHistory.route) {
                MedicalHistoryScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onContinueClicked = { navController.navigate(Screen.LifestyleDetails.route) }
                )
            }
            composable(Screen.LifestyleDetails.route) {
                LifestyleDetailsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onContinueClicked = { navController.navigate(Screen.PrivacyAndConsent.route) }
                )
            }
            composable(Screen.PrivacyAndConsent.route) {
                PrivacyAndConsentScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onCompleteSetupClicked = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onHealthScreeningClicked = { navController.navigate(Screen.HealthScreening.route) },
                    onMedicalScreeningClicked = { navController.navigate(Screen.MedicalScreening.route) },
                    onAiHealthChatClicked = { navController.navigate(Screen.AiHealthAssistant.route) },
                    onDoctorChatClicked = { navController.navigate(Screen.Messages.route) },
                    onRecentActivityClicked = { reportName -> navController.navigate(Screen.ReportDetails.route.replace("{reportName}", reportName)) },
                    onConsultDoctorClicked = { navController.navigate(Screen.ConsultDoctor.route) },
                    onNotificationsClicked = { navController.navigate(Screen.Notifications.route) }
                )
            }
            composable(Screen.DoctorHome.route) {
                DoctorHomeScreen(
                    onHomeClicked = { navController.navigate(Screen.DoctorHome.route) },
                    onCasesClicked = { navController.navigate(Screen.AllCases.route) },
                    onPatientsClicked = { navController.navigate(Screen.MyPatients.route) },
                    onProfileClicked = { navController.navigate(Screen.DoctorProfile.route) },
                    onNewAnalysisClicked = { navController.navigate(Screen.UploadDocuments.route) },
                    onPatientChatClicked = { patientName -> navController.navigate(Screen.DoctorPatientChat.route.replace("{patientName}", patientName)) },
                    onPriorityCaseClicked = { navController.navigate(Screen.CaseReportDetails.route) },
                    onNotificationsClicked = { navController.navigate(Screen.DoctorNotifications.route) }
                )
            }
            composable(Screen.Screening.route) {
                HealthScreeningScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onCategoryClicked = { navController.navigate(Screen.UploadMedicalReport.route) })
            }
            composable(Screen.History.route) {
                ScreeningHistoryScreen(
                    onScreeningClicked = { reportName -> navController.navigate(Screen.ReportDetails.route.replace("{reportName}", reportName)) }
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
            composable(Screen.DoctorProfile.route) {
                DoctorProfileScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onEditProfileClicked = { navController.navigate(Screen.EditProfile.route) },
                    onPrivacyAndDataClicked = { navController.navigate(Screen.PrivacyAndData.route) },
                    onSettingsClicked = { navController.navigate(Screen.Settings.route) },
                    onLogoutClicked = {
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    },
                    onHomeClicked = { navController.navigate(Screen.DoctorHome.route) },
                    onCasesClicked = { navController.navigate(Screen.AllCases.route) },
                    onPatientsClicked = { navController.navigate(Screen.MyPatients.route) }
                )
            }
            composable(Screen.HealthScreening.route) {
                HealthScreeningScreen(onNavigateBack = { navController.popBackStack() }, onCategoryClicked = { navController.navigate(Screen.UploadMedicalReport.route) })
            }
            composable(Screen.MedicalScreening.route) {
                MedicalScreeningScreen(onNavigateBack = { navController.popBackStack() }, onLifestyleClicked = { navController.navigate(Screen.LifestyleDetails.route) }, onRunAnalysisClicked = { navController.navigate(Screen.AnalyzingHealthProfile.route) })
            }
            composable(Screen.AiHealthAssistant.route) {
                AiHealthAssistantScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.Messages.route) {
                MessagesScreen(onNavigateBack = { navController.popBackStack() }, onConversationClicked = { doctorName ->
                    if (doctorName == "AI Health Assistant") {
                        navController.navigate(Screen.AiHealthAssistant.route)
                    } else {
                        navController.navigate(Screen.DoctorChat.route.replace("{doctorName}", doctorName))
                    }
                })
            }
            composable(Screen.ReportDetails.route) {
                val reportName = it.arguments?.getString("reportName") ?: ""
                ReportDetailsScreen(reportName = reportName, onNavigateBack = { navController.popBackStack() }, onDownloadClicked = { navController.navigate(Screen.ShareReport.route) }, onCompareClicked = { navController.navigate(Screen.CompareResults.route) })
            }
            composable(Screen.ConsultDoctor.route) {
                ConsultDoctorScreen(onNavigateBack = { navController.popBackStack() }, onDoctorClicked = { doctorName -> navController.navigate(Screen.BookAppointment.route.replace("{doctorName}", doctorName)) })
            }
            composable(Screen.Notifications.route) {
                NotificationsScreen(onNavigateBack = { navController.popBackStack() }, onScreeningClicked = { reportName -> navController.navigate(Screen.ReportDetails.route.replace("{reportName}", reportName)) }, onHealthReminderClicked = {})
            }
            composable(Screen.UploadMedicalReport.route) {
                UploadMedicalReportScreen(onNavigateBack = { navController.popBackStack() }, onRunAnalysisClicked = { navController.navigate(Screen.AnalyzingHealthProfile.route) })
            }
            composable(Screen.AnalyzingHealthProfile.route) {
                AnalyzingHealthProfileScreen(onAnalysisComplete = { navController.navigate(Screen.PrediagnosisReport.route) })
            }
            composable(Screen.PrediagnosisReport.route) {
                PrediagnosisReportScreen(onNavigateBack = { navController.popBackStack() }, onDownloadReportClicked = { navController.navigate(Screen.ShareReport.route) }, onBookConsultClicked = { navController.navigate(Screen.ConsultDoctor.route) })
            }
            composable(Screen.EditProfile.route) {
                EditProfileScreen(onNavigateBack = { navController.popBackStack() }, onSaveChanges = { navController.popBackStack() })
            }
            composable(Screen.PrivacyAndData.route) {
                PrivacyAndDataScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.Settings.route) {
                SettingsScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.BookAppointment.route) { backStackEntry ->
                val doctorName = backStackEntry.arguments?.getString("doctorName") ?: ""
                BookAppointmentScreen(onNavigateBack = { navController.popBackStack() }, doctorName = doctorName, onConfirmBookingClicked = { navController.navigate(Screen.BookingStatus.route.replace("{doctorName}", doctorName)) })
            }
            composable(Screen.ShareReport.route) {
                ShareReportScreen(onNavigateBack = { navController.popBackStack() }, onContinueClicked = { navController.navigate(Screen.Feedback.route) })
            }
            composable(Screen.UploadDocuments.route) {
                UploadDocumentsScreen(onNavigateBack = { navController.popBackStack() }, onContinueClicked = { navController.navigate(Screen.ClinicalAnalysisInProgress.route) })
            }
            composable(Screen.ClinicalAnalysisInProgress.route) {
                ClinicalAnalysisInProgressScreen(onAnalysisComplete = { navController.navigate(Screen.ClinicalAnalysisReport.route) })
            }
            composable(Screen.ClinicalAnalysisReport.route) {
                ClinicalAnalysisReportScreen(onNavigateBack = { navController.popBackStack() }, onViewFullReportClicked = { navController.navigate(Screen.RiskDetails.route) }, onNewScreeningClicked = { navController.navigate(Screen.UploadDocuments.route) })
            }
            composable(Screen.Feedback.route) {
                FeedbackScreen(
                    onNavigateBack = { navController.popBackStack() }, 
                    onSubmit = { navController.navigate(Screen.ThankYou.route) { popUpTo(Screen.Home.route) } }, 
                    onSkip = { navController.navigate(Screen.Home.route) { popUpTo(navController.graph.id) { inclusive = true } } }
                )
            }
            composable(Screen.BookingStatus.route) { backStackEntry ->
                val doctorName = backStackEntry.arguments?.getString("doctorName") ?: ""
                BookingStatusScreen(onNavigateBack = { navController.popBackStack() }, doctorName = doctorName, onViewAppointmentsClicked = { navController.navigate(Screen.MyAppointments.route) }, onBackToHomeClicked = { navController.navigate(Screen.Home.route) })
            }
            composable(Screen.DoctorChat.route) { backStackEntry ->
                val doctorName = backStackEntry.arguments?.getString("doctorName") ?: ""
                DoctorChatScreen(doctorName = doctorName, onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.ThankYou.route) {
                ThankYouScreen(onTimeout = { navController.navigate(Screen.Home.route) { popUpTo(navController.graph.id) { inclusive = true } } })
            }
            composable(Screen.MyAppointments.route) {
                MyAppointmentsScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.RiskDetails.route) {
                RiskDetailsScreen(onNavigateBack = { navController.popBackStack() }, onViewProbabilityAnalysisClicked = { navController.navigate(Screen.ProbabilityAnalysis.route) })
            }
            composable(Screen.ProbabilityAnalysis.route) {
                ProbabilityAnalysisScreen(onNavigateBack = { navController.popBackStack() }, onViewRecommendationsClicked = { navController.navigate(Screen.Recommendations.route) })
            }
            composable(Screen.Recommendations.route) {
                RecommendationsScreen(onNavigateBack = { navController.popBackStack() }, onViewPreventiveTipsClicked = { navController.navigate(Screen.PreventiveCare.route) })
            }
            composable(Screen.PreventiveCare.route) {
                PreventiveCareScreen(onNavigateBack = { navController.popBackStack() }, onContinueClicked = { navController.navigate(Screen.Consultation.route) })
            }
            composable(Screen.Consultation.route) {
                ConsultationScreen(onNavigateBack = { navController.popBackStack() }, onDownloadAndShareClicked = { navController.navigate(Screen.ShareReport.route) })
            }
            composable(Screen.AllCases.route) {
                AllCasesScreen(onCaseClicked = { navController.navigate(Screen.CaseReportDetails.route) })
            }
            composable(Screen.CaseReportDetails.route) {
                CaseReportDetailsScreen(onNavigateBack = { navController.popBackStack() }, onHistoryClicked = { navController.navigate(Screen.CompareResults.route) })
            }
            composable(Screen.CompareResults.route) {
                CompareResultsScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.MyPatients.route) {
                MyPatientsScreen(onPatientClicked = { navController.navigate(Screen.CaseReportDetails.route) })
            }
            composable(Screen.DoctorPatientChat.route) { backStackEntry ->
                val patientName = backStackEntry.arguments?.getString("patientName") ?: ""
                DoctorPatientChatScreen(patientName = patientName, onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.DoctorNotifications.route) {
                DoctorNotificationsScreen(onNavigateBack = { navController.popBackStack() }, onNotificationClicked = { navController.navigate(Screen.CaseReportDetails.route) })
            }
        }
    }
}
