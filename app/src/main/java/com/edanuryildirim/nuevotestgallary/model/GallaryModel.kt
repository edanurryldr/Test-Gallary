package com.edanuryildirim.nuevotestgallary.model

import java.io.Serializable

data class GallaryModel(
    var albumId : Int,
    var id : Int,
    var title : String,
    var name : String,
    var email : String,
    var body : String,
    var url : String,
    var thumbnailUrl : String
): Serializable