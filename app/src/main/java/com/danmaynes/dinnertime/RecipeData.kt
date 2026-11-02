package com.danmaynes.dinnertime

data class Recipe(
    val id: Int,
    val title: String,
    val source: String,
    val ingredients: List<String>,
    val steps: List<String>
)

object RecipeData {
    val recipes = listOf(
        Recipe(
            id = 0,
            title = "Spaghetti Squash with Ricotta, Pesto, and Feta",
            source = "From a Facebook reel by Miranda Taylor",
            ingredients = listOf(
                "1 spaghetti squash",
                "Ricotta cheese — amount not stated",
                "Pesto — amount not stated",
                "Feta — amount not stated",
                "Sun-dried tomatoes — amount not stated",
                "Avocado oil — amount not stated, use not detailed",
                "Salt — amount not stated"
            ),
            steps = listOf(
                "Cut the spaghetti squash in half and hollow it out.",
                "Fill with ricotta and pesto.",
                "Top with feta and sun-dried tomatoes.",
                "Cover in tin foil and bake at 400 for 35–45 minutes."
            )
        )
    )
}
