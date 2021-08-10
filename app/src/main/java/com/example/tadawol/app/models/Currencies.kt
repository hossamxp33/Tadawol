package com.example.tadawol.app.models

data class Currencies(
    var `data`: List<Data>,
    var pagination: Pagination,
    var success: Boolean
)