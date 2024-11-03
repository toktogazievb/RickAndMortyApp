package com.example.rickandmortyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        getCharacters()
    }

    private fun init() {
        val adapter = CharacterAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getCharacters() {
        viewModel.getCharacter()
        viewModel.character.observe(this) { character ->
            (binding.recyclerView.adapter as CharacterAdapter).setData(character)
        }
        viewModel.error.observe(this){error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

}