package com.personalsprojects.androidcompose.ui.screens.favoritesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _state: MutableStateFlow<List<Hero>> = MutableStateFlow(listOf())
    val state: StateFlow<List<Hero>> = _state.asStateFlow()

     val stateRepository: StateFlow<List<Hero>> = repository.heroesFlow


    init {
        launchFlow()
        getFavorites()
    }


    private fun launchFlow(){
        viewModelScope.launch(Dispatchers.IO) {
            stateRepository.collect{heroes ->
                _state.update { heroes.filter{ it.favorite} }
            }
        }
    }

    private fun getFavorites(){
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                    repository.getHeroes()
            }
        }
    }

    // TODO: See how to refactor, "same" function in HeroListViewModel
    fun onPressLike(hero: HeroLocal){
        hero.favorite = !hero.favorite
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                    repository.updateHero(hero)
            }
        }
    }

}
