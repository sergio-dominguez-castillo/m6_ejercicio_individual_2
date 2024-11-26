package cl.bootcamp.ejercicioinividual2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.ejercicioinividual2.model.Contacts
import cl.bootcamp.ejercicioinividual2.repository.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val repository: ContactsRepository) :
    ViewModel() {

    private val _contactsList: MutableStateFlow<List<Contacts>> =
        MutableStateFlow<List<Contacts>>(emptyList())
    val contactList: StateFlow<List<Contacts>> = _contactsList.asStateFlow()

    // la primera llamada es a toda la tabla y carga la lista
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContact().collect { item ->
                if (item.isNullOrEmpty()) {
                    _contactsList.value = emptyList()
                } else {
                    _contactsList.value = item

                }
            }
        }
    }

    // ahora para crear, actualizar y borrar
    fun addContact(contact: Contacts) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contact)
        }
    }

    fun updateContact(contact: Contacts) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contacts) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }
}

