package com.edanuryildirim.nuevotestgallary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edanuryildirim.nuevotestgallary.adapter.RecyclerViewAdapter
import com.edanuryildirim.nuevotestgallary.databinding.ActivityMainBinding
import com.edanuryildirim.nuevotestgallary.model.GallaryModel
import com.edanuryildirim.nuevotestgallary.service.GallaryAPI
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private lateinit var binding : ActivityMainBinding
    private var gallaryModels : ArrayList<GallaryModel>? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var recyclerViewAdapterGallray : RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,1)
        binding.recyclerView.layoutManager = layoutManager

        loadData()


    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GallaryAPI::class.java)
        val call = service.getGallary()

        call.enqueue(object : Callback<List<GallaryModel>> {
            override fun onResponse(
                call: Call<List<GallaryModel>>,
                response: Response<List<GallaryModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        gallaryModels = ArrayList(it)

                        gallaryModels?.let {
                            recyclerViewAdapterGallray = RecyclerViewAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapterGallray
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<GallaryModel>>, t: Throwable) {
                t.printStackTrace()            }
        })
    }
    override fun onItemClick(gallary: GallaryModel) {
        val intent = Intent(applicationContext, ViewDetail::class.java)
        intent.putExtra("data",gallary)
        startActivity(intent)    }

    }
