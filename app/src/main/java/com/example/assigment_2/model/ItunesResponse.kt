package com.example.assigment_2.model

//"artistName":
//"collectionName":
//"artworkUrl60":
//"trackPrice":

data class ItunesResponse(
    val resultCount: Int,
    val results: List<ItunesInfo>
)

data class ItunesInfo(
    val artistId: Int,
    val artistName: String,
    val artworkUrl60: String,
    val collectionName: String,
    val trackPrice: Double,
)