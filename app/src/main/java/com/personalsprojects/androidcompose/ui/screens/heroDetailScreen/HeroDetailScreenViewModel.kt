package com.personalsprojects.androidcompose.ui.screens.heroDetailScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.states.HeroDetailState
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
class HeroDetailScreenViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state: MutableStateFlow<HeroDetailState> = MutableStateFlow(HeroDetailState.Loading)
    val state: StateFlow<HeroDetailState> = _state.asStateFlow()



    fun getHero(heroID: String){
        viewModelScope.launch {
            _state.update { HeroDetailState.Loading }

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getNetworkHeroByID(heroID)
                }
            }
            Log.d("HEROESDETAIL", "RESULT $result");
            if (result.isSuccess) {
                Log.d("HEROESDETAIL", "ENTRA EN EL succes VM");
                _state.update { HeroDetailState.Success(result.getOrThrow()) }
            } else {
                Log.d("HEROESDETAIL", "ENTRA EN EL else VM");
                _state.update { HeroDetailState.Error (result.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }


    fun resetState(){
        _state.update { HeroDetailState.Loading }
    }

}