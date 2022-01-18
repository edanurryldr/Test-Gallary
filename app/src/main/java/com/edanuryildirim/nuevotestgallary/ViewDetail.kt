package com.edanuryildirim.nuevotestgallary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edanuryildirim.nuevotestgallary.databinding.ActivityViewDetailBinding
import com.edanuryildirim.nuevotestgallary.model.GallaryModel
import com.edanuryildirim.nuevotestgallary.service.GallaryAPI
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewDetail : AppCompatActivity() {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private lateinit var binding : ActivityViewDetailBinding
    private var gallaryModels : ArrayList<GallaryModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedData = intent.getSerializableExtra("data") as GallaryModel

        binding.textViewTitle.text = selectedData.title

        val url2 = selectedData.url
        Picasso.get().load(url2).into(binding.imgFirstindex)

        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GallaryAPI::class.java)
        val call = service.getDetailGallary()

        call.enqueue(object : Callback<List<GallaryModel>> {
            override fun onResponse(
                call: Call<List<GallaryModel>>,
                response: Response<List<GallaryModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        gallaryModels = ArrayList(it)
                            for(gallaryModel : GallaryModel in gallaryModels!!){
                                binding.textViewBody.text = gallaryModel.body
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<GallaryModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}