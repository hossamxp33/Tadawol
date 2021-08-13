package com.example.tadawol.app.models

data class Profit (
    val profittotal: List<ProfitElement>? = null,
    val profit: List<ProfitElement>? = null
)

data class ProfitElement (
    val id: Long? = null,
    val currencyID: Long? = null,
    val enter: Long? = null,
    val stopProfit: Double? = null,
    val notes: String? = null,
    val created: String? = null,
    val modified: String? = null,
    val vips: String? = null,
    val stopLoss: Long? = null,
    val tradeStatus: Float? = null,
    val profit: Float? = null,
    val month: Int? = null

)
