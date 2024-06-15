package org.d3if3114.assessment3.navigation

sealed class Screen(val  route: String) {
    data object Home : Screen("mainScreen")
    data object About : Screen("aboutScreen")
}