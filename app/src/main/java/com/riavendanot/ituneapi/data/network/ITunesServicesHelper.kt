package com.riavendanot.ituneapi.data.network

class ITunesServicesHelper(
    private val services: ITunesApiServices
) {
    suspend fun searchTerm(term: String, offset: Int) = services.searchTerm(term = term, offset = offset)
}