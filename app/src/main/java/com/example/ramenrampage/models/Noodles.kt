package com.example.ramenrampage.models

data class Noodles(

    val brand : String = " ",
    val name : String = " ",
    //val producent : String = "",
    val variety : String = " ",
    val style : String = " ",
    val country : String = " ",


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


