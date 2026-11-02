package com.danmaynes.dinnertime

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val prefs = getSharedPreferences("dinner_menu", MODE_PRIVATE)
        val selected = RecipeData.recipes.filter { prefs.getBoolean("menu_${it.id}", false) }
        val recipesView = findViewById<TextView>(R.id.menu_recipes)
        val ingredientsView = findViewById<TextView>(R.id.menu_ingredients)
        if (selected.isEmpty()) {
            recipesView.text = "(No recipes selected yet.)"
            ingredientsView.text = ""
        } else {
            recipesView.text = selected.joinToString("\n") { "• ${it.title}" }
            val combined = linkedSetOf<String>()
            for (recipe in selected) {
                combined.addAll(recipe.ingredients)
            }
            ingredientsView.text = combined.joinToString("\n") { "• $it" }
        }
    }
}
