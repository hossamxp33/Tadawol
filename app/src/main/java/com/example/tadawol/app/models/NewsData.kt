package com.example.tadawol.app.models

data class NewsData(
    var news: List<New>
)
data class New(
    var created: String,
    var description: String,
    var id: Int,
    var photo: String,
    var title: String
)