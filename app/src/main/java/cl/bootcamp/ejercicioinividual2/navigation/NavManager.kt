package cl.bootcamp.ejercicioinividual2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.ejercicioinividual2.view.AddView
import cl.bootcamp.ejercicioinividual2.view.EditView
import cl.bootcamp.ejercicioinividual2.view.HomeView
import cl.bootcamp.ejercicioinividual2.viewModel.AgendaViewModel
import cl.bootcamp.ejercicioinividual2.viewModel.ContactsViewModel

@Composable
fun NavManager(
    modifier: Modifier,
    contactsViewModel: ContactsViewModel,
    agendaViewModel: AgendaViewModel

) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, contactsViewModel)
        }

        composable("AddView") {
            AddView(navController, contactsViewModel, agendaViewModel)
        }

        composable("EditView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )) {
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, contactsViewModel, agendaViewModel, id)
        }
    }
}