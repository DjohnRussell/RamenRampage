package com.example.ramenrampage.ui.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ramenrampage.models.Noodles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class searchViewModel : ViewModel(){

    private val _searchText =  MutableStateFlow(" ")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow( false)
    val isSearching = _isSearching.asStateFlow()

    private val _noddles = MutableStateFlow(allNoodles)
    val noodles = searchText
        .combine(_noddles) { text, noodles ->
            if (text.isNotBlank()) {
                noodles
            }else {
                noodles.filter {
                   it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _noddles.value
        )

    fun onSearchTextChange(text : String) {
        _searchText.value = text
    }

}

// just adding data (remove when firestore is up)
private val allNoodles = listOf(

    Noodles(
        "Nissin",
        "Demae Ramen Biff",
        "Pack",
        "Egg-Noodle",
        "Japan",

    ),

    Noodles(
        "Shin Ramyun",
        "Black",
        "Pack",
        "Egg-Noodle",
        "South Korea",

    ),
    Noodles(
        "Mr.Lee",
        "Chicken",
        "Pack",
        "Egg-Noodle",
        "Norway",

    ),
    Noodles(
        "Samyang",
        "Spicy Flavor",
        "Pack",
        "Egg-Noodle",
        "South Korea",

    ),
    Noodles(
        "Buldak",
        "HOT Chicken Flavor Ramen",
        "Pack",
        "Egg-Noodle",
        "South Korea",

    ),






)

