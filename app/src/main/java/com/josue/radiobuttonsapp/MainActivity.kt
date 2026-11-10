package com.josue.radiobuttonsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

/**
 * Actividad Principal - Menú de Ejercicios
 * Materia: Aplicaciones Móviles - Semana 2: RadioButtons
 * Estudiante: Josué
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de botones y tarjetas del menú
        val btnAbrirEncuesta = findViewById<Button>(R.id.btnAbrirEncuesta)
        val cardEncuesta = findViewById<MaterialCardView>(R.id.cardEncuesta)

        val btnAbrirTema = findViewById<Button>(R.id.btnAbrirTema)
        val cardTema = findViewById<MaterialCardView>(R.id.cardTema)

        val btnAbrirQuiz = findViewById<Button>(R.id.btnAbrirQuiz)
        val cardQuiz = findViewById<MaterialCardView>(R.id.cardQuiz)

        val btnAbrirPropinas = findViewById<Button>(R.id.btnAbrirPropinas)
        val cardPropinas = findViewById<MaterialCardView>(R.id.cardPropinas)

        val btnAbrirConversor = findViewById<Button>(R.id.btnAbrirConversor)
        val cardConversor = findViewById<MaterialCardView>(R.id.cardConversor)

        // 1. Ejercicio Encuesta de Satisfacción
        val abrirEncuesta = {
            startActivity(Intent(this, EncuestaActivity::class.java))
        }
        btnAbrirEncuesta.setOnClickListener { abrirEncuesta() }
        cardEncuesta.setOnClickListener { abrirEncuesta() }

        // 2. Ejercicio Selector de Tema
        val abrirTema = {
            startActivity(Intent(this, SelectorTemaActivity::class.java))
        }
        btnAbrirTema.setOnClickListener { abrirTema() }
        cardTema.setOnClickListener { abrirTema() }

        // 3. Ejercicio Quiz Simple
        val abrirQuiz = {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        btnAbrirQuiz.setOnClickListener { abrirQuiz() }
        cardQuiz.setOnClickListener { abrirQuiz() }

        // 4. Ejercicio Calculadora de Propinas
        val abrirPropinas = {
            startActivity(Intent(this, PropinasActivity::class.java))
        }
        btnAbrirPropinas.setOnClickListener { abrirPropinas() }
        cardPropinas.setOnClickListener { abrirPropinas() }

        // 5. Ejercicio Conversor de Unidades
        val abrirConversor = {
            startActivity(Intent(this, ConversorActivity::class.java))
        }
        btnAbrirConversor.setOnClickListener { abrirConversor() }
        cardConversor.setOnClickListener { abrirConversor() }
    }
}
