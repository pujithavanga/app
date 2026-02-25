package com.simats.prediagnostic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector? = null) {
    // Onboarding flow
    object Onboarding : Screen("onboarding")
    object RoleSelection : Screen("role_selection")
    object PatientSignUp : Screen("patient_signup")
    object DoctorSignUp : Screen("doctor_signup")
    object VerifyPhone : Screen("verify_phone")
    object ProfileSetup : Screen("profile_setup")
    object MedicalHistory : Screen("medical_history")
    object LifestyleDetails : Screen("lifestyle_details")
    object PrivacyAndConsent : Screen("privacy_and_consent")
    object Main : Screen("main")

    // Main app screens with bottom navigation
    object Home : Screen("home", Icons.Default.Home)
    object DoctorHome : Screen("doctor_home", Icons.Default.Home)
    object Screening : Screen("screening", Icons.Default.Search)
    object History : Screen("history", Icons.Default.History)
    object Profile : Screen("profile", Icons.Default.Person)

    // Other screens reachable from the main app
    object HealthScreening : Screen("healthScreening")
    object MedicalScreening : Screen("medicalScreening")
    object AiHealthAssistant : Screen("aiHealthAssistant")
    object Messages : Screen("messages")
    object ReportDetails : Screen("reportDetails/{reportName}")
    object ConsultDoctor : Screen("consultDoctor")
    object Notifications : Screen("notifications")
    object UploadMedicalReport : Screen("uploadMedicalReport")
    object EditProfile : Screen("editProfile")
    object PrivacyAndData : Screen("privacyAndData")
    object Settings : Screen("settings")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object DoctorLogin : Screen("doctor_login")
    object AnalyzingHealthProfile : Screen("analyzing_health_profile")
    object PrediagnosisReport : Screen("prediagnosis_report")
    object BookAppointment : Screen("book_appointment/{doctorName}")
    object ShareReport : Screen("share_report")
    object UploadDocuments : Screen("upload_documents")
    object ClinicalAnalysisInProgress : Screen("clinical_analysis_in_progress")
    object ClinicalAnalysisReport : Screen("clinical_analysis_report")
    object DoctorProfile : Screen("doctor_profile")
    object Feedback : Screen("feedback")
    object BookingStatus : Screen("booking_status/{doctorName}")
    object DoctorChat : Screen("doctor_chat/{doctorName}")
    object ThankYou : Screen("thank_you/{userType}")
    object MyAppointments : Screen("my_appointments")
    object RiskDetails : Screen("risk_details")
    object ProbabilityAnalysis : Screen("probability_analysis")
    object Recommendations : Screen("recommendations")
    object PreventiveCare : Screen("preventive_care")
    object Consultation : Screen("consultation")
    object AllCases : Screen("all_cases")
    object CaseReportDetails : Screen("case_report_details")
    object CompareResults : Screen("compare_results")
    object MyPatients : Screen("my_patients")
    object DoctorPatientChat : Screen("doctor_patient_chat/{patientName}")
    object DoctorNotifications : Screen("doctor_notifications")
}
