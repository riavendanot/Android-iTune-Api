package com.riavendanot.ituneapi.data.network

class ITunesServicesHelper(
    private val services: ITunesApiServices
) {
    suspend fun searchTerm(term: String) = services.searchTerm(term)
}