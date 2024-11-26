package cl.bootcamp.ejercicioinividual2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "contacts")
data class Contacts(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    @ColumnInfo(name ="nombre")      val name: String,
    @ColumnInfo(name ="telefono")    val telephone: String,
    @ColumnInfo(name ="correo")      val email: String,
    @ColumnInfo(name ="imagenPerfil") val imageProfile: String,
    @ColumnInfo(name ="fechaNacimiento") val dateBirth: String
)
