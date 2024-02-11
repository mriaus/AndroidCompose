package com.personalsprojects.androidcompose.ui.screens.heroDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.domain.HeroDetailUI
import com.personalsprojects.androidcompose.states.HeroDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailScreenViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state: MutableStateFlow<HeroDetailState> = MutableStateFlow(HeroDetailState.Loading)
    val state: StateFlow<HeroDetailState> = _state.asStateFlow()


    fun obtainAllDetail(heroID: String){
        viewModelScope.launch {
            _state.update { HeroDetailState.Loading }
            try {
                val heroFlow = repository.getNetworkHeroByID(heroID)
                val seriesFlow = repository.getNetworkSeriesByHeroID(heroID)
                val comicsFlow = repository.getNetworkComicsByHeroID(heroID)

                val detailedHero = combine(heroFlow, seriesFlow, comicsFlow) { hero, series, comics ->
                        HeroDetailUI(name = hero.name,
                                    photo = hero.photo,
                                    description = hero.description,
                                    series,
                                    comics
                        )
                    }.single()

                _state.update { HeroDetailState.Success(detailedHero) }

            }catch (e: Exception) {
                _state.update { HeroDetailState.Error(e.message.orEmpty()) }
            }
        }
    }

    fun resetState(){
        _state.update { HeroDetailState.Loading }
    }

}