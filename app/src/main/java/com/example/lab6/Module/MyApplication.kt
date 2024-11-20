package com.example.lab6.Module

import android.app.Application
import androidx.room.Room
import com.example.lab6.Model.UserDB
import com.example.lab6.Retrofit.MainApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    private val appModule = module {

        // Инъекция Retrofit
        single {
            Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MainApi::class.java)
        }

        // Инъекция UserDB
        single {
            Room.databaseBuilder(applicationContext, UserDB::class.java, "User")
                .fallbackToDestructiveMigration()
                .build()
        }

        // Инъекция DAO из базы данных
        single { get<UserDB>().usersDao() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}


