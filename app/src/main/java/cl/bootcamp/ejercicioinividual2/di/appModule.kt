package cl.bootcamp.ejercicioinividual2.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.ejercicioinividual2.room.ContactsDataBaseDao
import cl.bootcamp.ejercicioinividual2.room.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    @Singleton
    @Provides
    fun providesContactsDao(contactsDatabase: ContactsDatabase): ContactsDataBaseDao{
        return contactsDatabase.contactsDao()
    }

    @Singleton
    @Provides
    fun provideContactsDatabase(@ApplicationContext context: Context): ContactsDatabase{
        return Room.databaseBuilder(
            context,
            ContactsDatabase::class.java,
            "contacts_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}