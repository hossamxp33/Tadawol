package com.example.tadawol.app.models

data class Price(
    var change: String ?=null,
    var changePercentage: String?=null,
    var chartFileUrl: Any?=null,
    var code: String?=null,
    var exchange: String?=null,
    var high: String?=null,
    var low: String?=null,
    var name: String?=null,
    var open: String?=null,
    var status: String?=null,
    var turnover: String?=null,
    var updatedAt: String?=null,
    var url: String?=null,
    var value: String?=null,
    var volume: String
)