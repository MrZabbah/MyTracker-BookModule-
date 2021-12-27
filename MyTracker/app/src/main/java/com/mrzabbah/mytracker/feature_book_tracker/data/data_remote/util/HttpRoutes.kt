package com.mrzabbah.mytracker.feature_book_tracker.data.data_remote.util

object HttpRoutes {

    private const val BASE_URL = "https://www.googleapis.com"
    const val VOLUMES = "$BASE_URL/books/v1/volumes"
    const val GOOGLE_BOOKS_API_KEY = "AIzaSyDV9fRubItcahlBaWUEE39rtDNmqot5f_4"
    const val GOOGLE_BOOKS_API_KEY2 = "AIzaSyCt--_KQPud07EvrNYf_rqWELPaOOforJg"
    const val TEST = "$VOLUMES?q=mistborn&inauthor=brandom+sanderson&maxResults=40&fields=items(volumeInfo(title))"
}