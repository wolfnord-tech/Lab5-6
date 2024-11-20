package com.example.lab6.Model



import android.util.Log
import com.example.lab6.Retrofit.MainApi

class UserRepository(private  val usersDao: UsersDao, private val UsersApi: MainApi) {

    suspend fun getUserFromApi(){
        val response = UsersApi.getUserById(1)
        Log.d("API INFO",response.id.toString()+" "+response.firstName+" "+response.lastName)
        for( i in 1..10) {
            val response = UsersApi.getUserById(i)

            val user = User(response.id, response.firstName,response.lastName)
            insert(user)
            Log.i("log","Загружено в БД")
        }
    }

    private suspend fun insert(user: User){
        usersDao.insert(user)
    }
    suspend fun getAllUsersFromBase() : List<User> {
        return usersDao.getAllUsers()
    }

}