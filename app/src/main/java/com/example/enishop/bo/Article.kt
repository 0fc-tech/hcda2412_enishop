package com.example.enishop.bo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    var name :String,
    var description : String,
    var price : Double,
    var urlImage : String,
    var category:String,
    var date:String
){}