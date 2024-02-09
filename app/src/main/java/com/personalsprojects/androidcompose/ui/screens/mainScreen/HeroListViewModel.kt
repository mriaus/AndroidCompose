package com.personalsprojects.androidcompose.ui.screens.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.states.HeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _state: MutableStateFlow<List<Hero>> = MutableStateFlow(listOf())
    val state: StateFlow<List<Hero>> = _state.asStateFlow()

     private val stateRepository: StateFlow<List<Hero>> = repository.heroesFlow


    init {
        Log.d("ViewModel", "Repository hash HEROLIST: ${repository.hashCode()}")
        launchFlow()
        getHeroes()
    }

    private fun launchFlow(){
        viewModelScope.launch(Dispatchers.IO) {
            stateRepository.collect{ newHeroes ->
                Log.d("FLOW", "Entra en el collect de herolist")
                _state.update { newHeroes }
            }
        }
    }

    private fun getHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHeroes()
        }
    }


    fun onPressLike(hero: HeroLocal){
        hero.favorite = !hero.favorite
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                    repository.updateHero(hero)
            }
        }
    }

}