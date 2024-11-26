package cl.bootcamp.ejercicioinividual2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.ejercicioinividual2.model.Contacts

@Database(entities = [Contacts::class], version = 1)
abstract class ContactsDatabase: RoomDatabase() {
    abstract fun contactsDao(): ContactsDataBaseDao
}