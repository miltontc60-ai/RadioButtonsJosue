package com.josue.radiobuttonsapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

/**
 * Ejercicio 2: Selector de Tema
 * Desarrollado por: Josué
 * Permite cambiar dinámicamente entre Modo Claro, Modo Oscuro y Seguir al Sistema
 * usando RadioButtons, aplicando la configuración con AppCompatDelegate y SharedPreferences.
 */
class SelectorTemaActivity : AppCompatActivity() {

    private val PREFS_NAME = "JosueThemePrefs"
    private val KEY_THEME = "selected_theme_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector_tema)

        val btnVolver = findViewById<Button>(R.id.btnVolverTema)
        val rgTema = findViewById<RadioGroup>(R.id.rgTema)
        val rbClaro = findViewById<RadioButton>(R.id.rbTemaClaro)
        val rbOscuro = findViewById<RadioButton>(R.id.rbTemaOscuro)
        val rbSistema = findViewById<RadioButton>(R.id.rbTemaSistema)
        val btnAplicar = findViewById<Button>(R.id.btnAplicarTema)
        val tvEstadoTema = findViewById<TextView>(R.id.tvEstadoTema)

        btnVolver.setOnClickListener {
            finish()
        }

        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedMode = prefs.getInt(KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // Marcar la opción guardada actualmente
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                rbClaro.isChecked = true
                tvEstadoTema.text = "Tema actual activo: Modo Claro ☀️"
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                rbOscuro.isChecked = true
                tvEstadoTema.text = "Tema actual activo: Modo Oscuro 🌙"
            }
            else -> {
                rbSistema.isChecked = true
                tvEstadoTema.text = "Tema actual activo: Predeterminado del Sistema ⚙️"
            }
        }

        // Función para aplicar y guardar el tema seleccionado
        val aplicarModo = { modo: Int, nombreModo: String ->
            prefs.edit().putInt(KEY_THEME, modo).apply()
            AppCompatDelegate.setDefaultNightMode(modo)
            tvEstadoTema.text = "Tema actual activo: $nombreModo"
            Toast.makeText(this, "Tema aplicado: $nombreModo", Toast.LENGTH_SHORT).show()
        }

        btnAplicar.setOnClickListener {
            when (rgTema.checkedRadioButtonId) {
                R.id.rbTemaClaro -> aplicarModo(AppCompatDelegate.MODE_NIGHT_NO, "Modo Claro ☀️")
                R.id.rbTemaOscuro -> aplicarModo(AppCompatDelegate.MODE_NIGHT_YES, "Modo Oscuro 🌙")
                R.id.rbTemaSistema -> aplicarModo(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "Predeterminado del Sistema ⚙️")
                else -> Toast.makeText(this, "Selecciona una opción de tema", Toast.LENGTH_SHORT).show()
            }
        }

        // Cambio inmediato al presionar los RadioButtons
        rgTema.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbTemaClaro -> aplicarModo(AppCompatDelegate.MODE_NIGHT_NO, "Modo Claro ☀️")
                R.id.rbTemaOscuro -> aplicarModo(AppCompatDelegate.MODE_NIGHT_YES, "Modo Oscuro 🌙")
                R.id.rbTemaSistema -> aplicarModo(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "Predeterminado del Sistema ⚙️")
            }
        }
    }
}
