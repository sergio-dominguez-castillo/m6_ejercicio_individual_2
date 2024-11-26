package cl.bootcamp.ejercicioinividual2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.bootcamp.ejercicioinividual2.model.Contacts
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDataBaseDao {
    // script de peticiones a la bbdd

    // todo los registros de la tabla contacts
    @Query("SELECT * FROM contacts")
    fun getContacts(): Flow<List<Contacts>>

    // solo registros por id
    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactsById(id: Long): Flow<Contacts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contacts)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contact: Contacts)

    @Delete
    suspend fun delete(contact: Contacts)
}