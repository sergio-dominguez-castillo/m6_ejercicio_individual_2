package cl.bootcamp.ejercicioinividual2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.bootcamp.ejercicioinividual2.navigation.NavManager
import cl.bootcamp.ejercicioinividual2.ui.theme.Ejercicioinividual2Theme
import cl.bootcamp.ejercicioinividual2.viewModel.AgendaViewModel
import cl.bootcamp.ejercicioinividual2.viewModel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val contactsViewModel: ContactsViewModel by viewModels()
        val agendaViewModel: AgendaViewModel by viewModels()
        setContent {
            Ejercicioinividual2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(
                        modifier = Modifier.padding(innerPadding),
                        contactsViewModel,
                        agendaViewModel
                    )
                }
            }
        }
    }
}
