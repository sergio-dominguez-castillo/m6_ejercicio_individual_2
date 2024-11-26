package cl.bootcamp.ejercicioinividual2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.bootcamp.ejercicioinividual2.components.MainButton
import cl.bootcamp.ejercicioinividual2.components.MainCharTextField
import cl.bootcamp.ejercicioinividual2.components.MainIconButton
import cl.bootcamp.ejercicioinividual2.components.MainNumTextField
import cl.bootcamp.ejercicioinividual2.components.MainTitle
import cl.bootcamp.ejercicioinividual2.components.Space
import cl.bootcamp.ejercicioinividual2.model.Contacts
import cl.bootcamp.ejercicioinividual2.viewModel.AgendaViewModel
import cl.bootcamp.ejercicioinividual2.viewModel.ContactsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(
    navController: NavController,
    contactsViewModel: ContactsViewModel,
    agendaViewModel: AgendaViewModel,
    id: Long
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Editar Contacto") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.AutoMirrored.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentEditView(it, navController, contactsViewModel, agendaViewModel, id)
    }
}

@Composable
fun ContentEditView(
    paddingValues: PaddingValues,
    navController: NavController,
    contactsViewModel: ContactsViewModel,
    agendaViewModel: AgendaViewModel,
    id: Long
) {
    val state = agendaViewModel.state
    // funcion para recuperar la informacion de la bbdd local del id
    LaunchedEffect(Unit) {
        agendaViewModel.getAgendaById(id)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        MainCharTextField(
            value = state.name,
            onValueChange = { agendaViewModel.onValue(it, "name") },
            label = "Nombre"
        )
        Space()
        MainCharTextField(
            value = state.imageProfile,
            onValueChange = { agendaViewModel.onValue(it, "imageProfile") },
            label = "Imagen de perfil"
        )
        Space()
        MainNumTextField(
            value = state.telephone,
            onValueChange = { agendaViewModel.onValue(it, "telephone") },
            label = "Telefono"
        )
        Space()
        MainCharTextField(
            value = state.email,
            onValueChange = { agendaViewModel.onValue(it, "email") },
            label = "Correo"
        )
        Space()
        MainNumTextField(
            value = state.dateBirth,
            onValueChange = { agendaViewModel.onValue(it, "dateBirth") },
            label = "Fecha Nacimiento"
        )
        Space()
        MainButton(
            text = "Actualizar"
        ) {

            // añadir registro

            contactsViewModel.updateContact(
                Contacts(
                    name = state.name,
                    telephone = state.telephone,
                    email = state.email,
                    imageProfile = state.imageProfile,
                    dateBirth = state.dateBirth
                )
            )
            // limpieza de las variables
            agendaViewModel.cleanContactState()

            // retorno a la vista principal
            navController.navigateUp()
            //navController.popBackStack()

        }
    }

}
