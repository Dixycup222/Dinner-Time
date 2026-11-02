package com.danmaynes.dinnertime

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val recipeId = intent.getIntExtra("recipe_id", 0)
        val recipe = RecipeData.recipes.firstOrNull { it.id == recipeId } ?: RecipeData.recipes[0]
        findViewById<TextView>(R.id.recipe_title).text = recipe.title
        findViewById<TextView>(R.id.recipe_source).text = recipe.source
        findViewById<TextView>(R.id.recipe_ingredients).text =
            recipe.ingredients.joinToString("\n") { "• $it" }
        findViewById<TextView>(R.id.recipe_steps).text =
            recipe.steps.mapIndexed { index, step -> "${index + 1}. $step" }.joinToString("\n")
        val prefs = getSharedPreferences("dinner_menu", MODE_PRIVATE)
        val checkBox = findViewById<CheckBox>(R.id.menu_checkbox)
        checkBox.isChecked = prefs.getBoolean("menu_${recipe.id}", false)
        checkBox.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("menu_${recipe.id}", checked).apply()
        }
    }
}
