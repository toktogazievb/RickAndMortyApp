package com.example.rickandmortyapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.ItemCharacterBinding
import com.example.rickandmortyapp.model.Character
import com.example.rickandmortyapp.viewmodel.CharacterViewModel

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<Character>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newCharacters: List<Character>){
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(character: Character){
            Glide.with(binding.imgCharacter).load(character.image).into(binding.imgCharacter)
            binding.txtCharacterName.text = character.name
            binding.txtCharacterSpecies.text = character.species
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(characters[position])
    }
}