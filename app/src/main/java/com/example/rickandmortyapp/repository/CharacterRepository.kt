package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.data.RickAndMortyApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: RickAndMortyApiService
) {
    fun getCharacter(page: Int) = apiService.getCharacters(page)
}