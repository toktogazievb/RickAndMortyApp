package com.example.rickandmortyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.model.Character
import com.example.rickandmortyapp.model.CharacterResponse
import com.example.rickandmortyapp.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _character = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>> get() = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCharacter(page: Int = 1) {
        repository.getCharacter(page).enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _character.value = response.body()?.results
                } else {
                    _error.value = "Ошибка загрузки данных"
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("API", "Failure: ${t.message}", t)
            }

        })
    }
}