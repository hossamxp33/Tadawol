package com.example.tadawol.app.models

data class StockPrices(
    var obj: Obj,
    var obj2: Obj
)
data class Obj(
    var lastUpdate: String,
    var prices: List<Price>
)
