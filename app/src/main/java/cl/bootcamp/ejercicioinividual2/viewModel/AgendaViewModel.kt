package cl.bootcamp.ejercicioinividual2.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.ejercicioinividual2.repository.ContactsRepository
import cl.bootcamp.ejercicioinividual2.state.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(private val repository: ContactsRepository): ViewModel(){

    var state by mutableStateOf(ContactState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "name" -> state = state.copy(name = value)
            "telephone" -> state = state.copy(telephone = value)
            "email" -> state = state.copy(email = value)
            "imageProfile" -> state = state.copy(imageProfile = value)
            "dateBirth" -> state = state.copy(dateBirth = value)
        }
    }

    fun cleanContactState() {
        state = state.copy(name = "")
        state = state.copy(telephone = "")
        state = state.copy(email = "")
        state = state.copy(imageProfile = "")
        state = state.copy(dateBirth= "")

    }

    fun getAgendaById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getContactsById(id).collect { item ->
                if (item != null) {
                    state = state.copy(name = item.name)
                    state = state.copy(telephone = item.telephone)
                    state = state.copy(email = item.email)
                    state = state.copy(imageProfile = item.imageProfile)
                    state = state.copy(dateBirth = item.dateBirth)
                } else {
                    Log.d("Error", "El objeto agenda id={id} es nulo")
                }
            }
        }
    }



}