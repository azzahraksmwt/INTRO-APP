package com.androidstudio.intro

//import android.content.Intent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import android.widget.ImageButton
//import android.widget.Toast
//import com.androidstudio.intro.ApiLogin.ApiResponseLogin
//import com.androidstudio.intro.ApiLogin.ApiServiceLogin
//import com.androidstudio.intro.databinding.ActivityLoginBinding
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
//    private var binding : ActivityLoginBinding? = null
//    private var user : String = ""
//    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnBack = findViewById<ImageButton>(R.id.btnback)
        val btnsubmit = findViewById<ImageButton>(R.id.btnsubmit)

        btnBack.setOnClickListener {
            finish()
        }

        btnsubmit.setOnClickListener{
            val intent = Intent(this,Dashboard::class.java)
            startActivity(intent)
        }

//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding!!.root)
//
//        binding!!.btnsubmit.setOnClickListener {
//            user = binding!!.username.text.toString()
//            pass = binding!!.password.text.toString()
//
//            when {
//                user == "" -> {
//                    binding!!.username.error = "Username can't be empty"
//                }
//
//                pass == "" -> {
//                    binding!!.password.error = "Password can't be empty"
//                }
//
//                else -> {
//                    getData()
//                }
//            }
//        }
    }

//    private suspend fun loginUser(email: String, password: String): ApiResponseLogin {
//        return withContext(Dispatchers.IO) {
//            // Panggil fungsi login dari API Retrofit di dalam coroutine
//            ApiServiceLogin.login(email, password)
//        }
//    }
//
//        private fun getData() {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://192.168.43.48:8000")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            val apiService = retrofit.create(ApiServiceLogin::class.java)
//
//            apiService.login(user, pass).enqueue(object : Callback<ApiResponseLogin> {
//                override fun onResponse(call: Call<ApiResponseLogin>, response: Response<ApiResponseLogin>) {
//                    if (response.isSuccessful) {
//                        if (response.body()?.response == true) {
//                            startActivity(Intent(this@Login, Dashboard::class.java))
//                            finish()
//                        } else {
//                            Toast.makeText(
//                                applicationContext,
//                                "Login gagal, Periksa kembali username dan password",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "Login gagal, Periksa kembali username dan password",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ApiResponseLogin>, t: Throwable) {
//                    Log.e("pesan error",  "${t.message}")
//                }
//            })
//        }
}