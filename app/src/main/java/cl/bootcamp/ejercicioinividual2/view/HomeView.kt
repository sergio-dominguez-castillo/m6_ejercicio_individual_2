package cl.bootcamp.ejercicioinividual2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cl.bootcamp.ejercicioinividual2.components.FloatButton
import cl.bootcamp.ejercicioinividual2.components.MainTitle
import cl.bootcamp.ejercicioinividual2.viewModel.ContactsViewModel
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.unit.dp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import cl.bootcamp.ejercicioinividual2.components.ContactCard
import cl.bootcamp.ejercicioinividual2.viewModel.AgendaViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    contactsViewModel: ContactsViewModel
   ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Ejercicio Individual 2") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, contactsViewModel)
    }
}

@Composable
fun ContentHomeView(
    paddingValues: PaddingValues,
    navController: NavController,
    contactsViewModel: ContactsViewModel
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        val contactList by contactsViewModel.contactList.collectAsState()
        LazyColumn {
            items(contactList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(image = Icons.Default.Delete),
                    background = MaterialTheme.colorScheme.error,
                    onSwipe = { contactsViewModel.deleteContact(item) }
                )
                SwipeableActionsBox(
                    startActions = listOf(delete),
                    endActions = listOf(delete),
                    swipeThreshold = 150.dp
                ) {
                    ContactCard(
                        name = item.name,
                        telephone = item.telephone,
                        email = item.email,
                        imageProfile = item.imageProfile,
                        dateBirth = item.dateBirth
                    ) {
                        navController.navigate("EditView/${item.id}")
                    }
                }
            }
        }
    }

}
