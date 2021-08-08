package com.example.tadawol.app.models

data class Currency(
    var created: String,
    var id: Int,
    var modified: String,
    var name: String,
    var price_buy: Int,
    var price_sell: Int
)