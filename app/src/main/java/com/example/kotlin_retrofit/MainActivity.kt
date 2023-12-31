package com.example.kotlin_retrofit

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_retrofit.databinding.ActivityMainBinding
import com.example.kotlin_retrofit.ui.theme.Kotlin_RetrofitTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private lateinit var binding: ActivityMainBinding
class MainActivity : ComponentActivity() {

    lateinit var dataItemAdapter: DataItemAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerviewData.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerviewData.layoutManager = linearLayoutManager

        getMyData()
    }
    private fun getMyData() {
        val rfBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val rfData = rfBuilder.getData()

        rfData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                val responseBody = response.body()!!

                dataItemAdapter = DataItemAdapter(baseContext, responseBody)
                dataItemAdapter.notifyDataSetChanged()

                binding.recyclerviewData.adapter = dataItemAdapter


            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("mainactivity", "onFailure " +t.message)
            }
        })
    }
}