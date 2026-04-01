package com.example.enishop.bo

data class Article(
    var id : Long,
    var name :String,
    var description : String,
    var price : Double,
    var urlImage : String,
    var category:String,
    var date:String
){}