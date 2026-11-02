package com.danmaynes.dinnertime

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.menu_button).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("dinner_menu", MODE_PRIVATE)
        val list = findViewById<LinearLayout>(R.id.recipe_list)
        list.removeAllViews()
        for (recipe in RecipeData.recipes) {
            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            val checkBox = CheckBox(this)
            checkBox.isChecked = prefs.getBoolean("menu_${recipe.id}", false)
            checkBox.setOnCheckedChangeListener { _, checked ->
                prefs.edit().putBoolean("menu_${recipe.id}", checked).apply()
            }
            val button = Button(this)
            button.text = recipe.title
            button.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            button.setOnClickListener {
                val intent = Intent(this, RecipeActivity::class.java)
                intent.putExtra("recipe_id", recipe.id)
                startActivity(intent)
            }
            row.addView(checkBox)
            row.addView(button)
            list.addView(row)
        }
    }
}
