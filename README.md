# Veda Technology Internship Companion App

## Project Overview
A comprehensive companion app for Veda Technology interns built using Kotlin, XML, Room Database, and JSON. It allows users to view domains, projects, and FAQs, and bookmark their favorite resources across their 4-day internship journey.

## Technologies Used
- **Kotlin & XML**: 100% Kotlin codebase paired with robust XML layouts adhering to Material Design 3 guidelines.
- **Room Database**: Local SQLite abstraction layer for persistent, offline-capable bookmarking and favorite item management.
- **RecyclerView**: High-performance lists for rendering domains, projects, FAQs, and favorites with custom ViewHolders.
- **JSON Parsing**: Structured curriculum and guidelines parsed dynamically via `org.json.JSONObject` from `res/raw/data.json`.
- **Jetpack Lifecycle & MVVM**: `ViewModel`, `LiveData`, and Kotlin Coroutines (`Dispatchers.IO`) for reactive, lifecycle-aware database transactions.

## Features
- **Branded Design Language:** Strict adherence to Veda Technology branding with Navy (`#0F172A`) and Orange (`#FF6B00`) themes.
- **Multi-Screen Modular Architecture:**
  - **Home (MainActivity):** Branded hero banner, cohort status, and 4 quick-access portal navigation cards.
  - **Tech Domains (DomainsActivity):** Displays internship tracks (Android, Web, AI/ML, Cloud & DevOps) with tech stacks and project counts.
  - **Projects (ProjectsActivity):** Full listing of Day 1 to Day 4 milestones and capstone assignments with duration, difficulty tags, and domain filters.
  - **Project Detail (ProjectDetailActivity):** Deep dive into project requirements, guidelines, and tech stack with a reactive one-tap **Bookmark** button.
  - **Intern FAQs (FaqActivity):** Interactive expandable answers regarding schedules, mentors, evaluation criteria, and certification.
  - **Favorites (FavoritesActivity):** Offline repository observing Room DB via `LiveData`, supporting project inspection, deletion, and undo snackbars.

## Outcome
- **Zero-Crash Stability:** Robust edge-to-edge padding, null-safe Intent extra extraction, and resilient database queries.
- **Offline-Ready:** All static data and bookmarked items remain accessible without requiring an active internet connection.
- **Intern Productivity:** Simplifies tracking day-to-day internship milestones and capstone requirements in a single unified dashboard.

---

## How to Build & Run
1. Open the project in **Android Studio**.
2. Sync the project with Gradle files.
3. Select an Android Emulator or connected physical device (API 24+).
4. Run `./gradlew assembleDebug` or click **Run** (`Shift + F10`).
