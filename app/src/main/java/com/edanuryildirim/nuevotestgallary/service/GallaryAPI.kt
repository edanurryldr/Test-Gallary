package com.edanuryildirim.nuevotestgallary.service

import com.edanuryildirim.nuevotestgallary.model.GallaryModel
import retrofit2.Call
import retrofit2.http.GET

interface GallaryAPI {

    @GET("photos")
    fun getGallary() : Call<List<GallaryModel>>

    @GET("comments?postId=1")
    fun getDetailGallary() : Call<List<GallaryModel>>

}