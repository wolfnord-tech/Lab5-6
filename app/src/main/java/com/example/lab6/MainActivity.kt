package com.example.lab6



import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.room.Room
//import com.example.lab6.Model.UserDB
import com.example.lab6.Model.UserRepository
import com.example.lab6.Model.UsersDao
import com.example.lab6.Retrofit.MainApi
import com.example.lab6.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressBar: ProgressBar

    private val UserApi: MainApi by inject()
    private val UserDao : UsersDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerView = binding.reycler
        val adapter = UserListAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val recept_button = binding.dogButton
        progressBar = binding.progressBar

        val user1 = UserRepository(UserDao,UserApi)

        recept_button.setOnClickListener{
            Log.i("log","Нажалось")
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                user1.getUserFromApi()
                val images = user1.getAllUsersFromBase()
                Log.i("log","Загружено из БД")
                withContext(Dispatchers.Main) {
                    adapter.updateData(images) // Передаем новые данные в адаптер
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                }
            }
        }

    }

}