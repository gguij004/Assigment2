package com.example.assigment_2.model.remote

//https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50
//https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50
//https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50
//https://www.googleapis.com/books/v1/volumes?q=bible&pr

//https://itunes.apple.com/search?term=rock&media=music&entity=song&startIndex=0

const val BASE_ITUNES_URL = "https://itunes.apple.com/"
const val BASE_ITUNES_ENDPOINT = "search"
const val PARAM_TERM = "term"
const val PARAM_MEDIA = "media"
const val PARAM_ENTITY = "entity"
const val PARAM_START_INDEX = "startIndex"