package com.example.tadawol.app.models

data class Subscriptions(
    var `data`: List<Data>,
    var pagination: Pagination,
    var success: Boolean
)