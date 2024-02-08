package com.personalsprojects.androidcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.states.HeroListState
import dagger.hilt.android.lifecycle.HiltViewModel
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


    private val _state: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.Loading)
    val state: StateFlow<HeroListState> = _state.asStateFlow()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            _state.update { HeroListState.Loading }

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getHeroes()
                }
            }
            if (result.isSuccess) {
                _state.update { HeroListState.Success(result.getOrThrow()) }
            } else {
                _state.update { HeroListState.Error(result.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }


    fun onPressLike(hero: HeroLocal){
        hero.favorite = !hero.favorite
        viewModelScope.launch {
           val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.updateHero(hero)
                }
            }
            if (result.isSuccess) {
                _state.update { HeroListState.Success(result.getOrThrow()) }
            } else {
                _state.update { HeroListState.Error(result.exceptionOrNull()?.message.orEmpty()) }
            }

        }
    }

}