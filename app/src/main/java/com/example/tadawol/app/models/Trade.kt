package com.example.tadawol.app.models

data class Trade(
    var created: String,
    var currency: Currency,
    var currency_id: Int,
    var enter: Int,
    var exitt: Int,
    var id: Int,
    var modified: String,
    var notes: String,
    var vips: String
)