package com.personalsprojects.androidcompose.ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _state: MutableStateFlow<List<Hero>> = MutableStateFlow(listOf())
    val state: StateFlow<List<Hero>> = _state.asStateFlow()

     private val stateRepository: Flow<List<Hero>> = repository.heroesFlow


    init {
        launchFlow()
        getHeroes()
    }

    private fun launchFlow(){
        viewModelScope.launch(Dispatchers.IO) {
            stateRepository.collect{ newHeroes ->
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