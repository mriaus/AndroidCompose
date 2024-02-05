package com.personalsprojects.androidcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
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
        getHeros()
    }

    fun getHeros() {
        viewModelScope.launch {
            _state.update { HeroListState.Loading }

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getHeroes()
                }
            }
            Log.d("HEROES", "RESULT $result");
            if (result.isSuccess) {
                Log.d("HEROES", "ENTRA EN EL succes VM");
                _state.update { HeroListState.Success(result.getOrThrow()) }
            } else {
                Log.d("HEROES", "ENTRA EN EL else VM");
                _state.update { HeroListState.Error(result.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }

}