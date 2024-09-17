package com.example.ramenrampage.models

data class Noodles(
    val review : Int = 0,
    val brand : String = " ",
    val variety : String = " ",
    val style : String = " ",
    val country : String = " ",
    val stars : Double = 0.0,
    val topTen : String? = null

)
{
    fun doesMatchSearchQuery(query : String): Boolean {
        val matchingCombination = listOf(
            "$brand",


        )

        return matchingCombination.any() {
            it.contains(query, ignoreCase = true)
        }
    }

}


