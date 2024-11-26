package cl.bootcamp.ejercicioinividual2.repository

import cl.bootcamp.ejercicioinividual2.model.Contacts
import cl.bootcamp.ejercicioinividual2.room.ContactsDataBaseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactsRepository @Inject constructor(private val contactsDataBaseDao: ContactsDataBaseDao) {

    // llama a las funciones CRUD de room
    suspend fun addContact(contact: Contacts) = contactsDataBaseDao.insert(contact)
    suspend fun updateContact(contact: Contacts) = contactsDataBaseDao.update(contact)
    suspend fun deleteContact(contact: Contacts) = contactsDataBaseDao.delete(contact)

    fun getAllContact(): Flow<List<Contacts>> = contactsDataBaseDao.getContacts()
        .flowOn(Dispatchers.IO).conflate()
    fun getContactsById(id: Long): Flow<Contacts> = contactsDataBaseDao.getContactsById(id)
        .flowOn(Dispatchers.IO).conflate()
}