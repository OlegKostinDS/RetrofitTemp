package com.example.retrofittemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://itunes.apple.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val filmService = retrofit.create(FilmApi::class.java)
    private lateinit var editText: EditText
    private lateinit var btn : Button
    private lateinit var text2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         editText = findViewById<EditText>(R.id.queryInput)
         btn = findViewById<Button>(R.id.getAppDataBtn)
        text2 = findViewById<TextView>(R.id.text2)

        btn.setOnClickListener{
            search()
        }

    }

    private fun search() {
        filmService.search(editText.text.toString()).enqueue(object : Callback<FilmResponse>{
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                if (response.isSuccessful){
                    text2.text =     response.body()?.results?.get(0)?.trackName .toString()
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
         Toast.makeText(this@MainActivity, "is it a problem? Yes it is", Toast.LENGTH_SHORT).show()
            }

        })
    }
}