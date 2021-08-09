package com.example.tadawol.app.models

data class Trade(
    var created: String,
    var currency: Currency,
    var currency_id: Double,
    var enter: Float,
    var stop_profit: Double,
    var stop_loss: Double,
    var trade_status: Int,
    var id: Int,
    var modified: String,
    var notes: String,
    var vips: String
)