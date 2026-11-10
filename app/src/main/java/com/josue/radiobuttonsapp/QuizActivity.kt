package com.josue.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

/**
 * Ejercicio 3: Quiz Simple
 * Desarrollado por: Josué
 * Implementa un cuestionario de opción múltiple con RadioButtons y cálculo de puntaje final.
 */
class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val btnVolver = findViewById<Button>(R.id.btnVolverQuiz)
        val rgP1 = findViewById<RadioGroup>(R.id.rgPregunta1)
        val rgP2 = findViewById<RadioGroup>(R.id.rgPregunta2)
        val rgP3 = findViewById<RadioGroup>(R.id.rgPregunta3)
        val rgP4 = findViewById<RadioGroup>(R.id.rgPregunta4)

        val btnEvaluar = findViewById<Button>(R.id.btnEvaluarQuiz)
        val btnReiniciar = findViewById<Button>(R.id.btnReiniciarQuiz)

        val cardResultado = findViewById<MaterialCardView>(R.id.cardResultadoQuiz)
        val tvPuntaje = findViewById<TextView>(R.id.tvPuntajeQuiz)
        val tvDetalle = findViewById<TextView>(R.id.tvDetalleRespuestas)

        btnVolver.setOnClickListener {
            finish()
        }

        btnEvaluar.setOnClickListener {
            val ans1 = rgP1.checkedRadioButtonId
            val ans2 = rgP2.checkedRadioButtonId
            val ans3 = rgP3.checkedRadioButtonId
            val ans4 = rgP4.checkedRadioButtonId

            if (ans1 == -1 || ans2 == -1 || ans3 == -1 || ans4 == -1) {
                Toast.makeText(this, "Por favor responde todas las preguntas antes de evaluar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var correctas = 0
            val total = 4
            val sb = StringBuilder()

            // Pregunta 1: R.id.p1_op1 -> RadioGroup
            if (ans1 == R.id.p1_op1) {
                correctas++
                sb.append("✓ Pregunta 1: Correcto (RadioGroup)\n")
            } else {
                sb.append("✗ Pregunta 1: Incorrecto (Respuesta correcta: RadioGroup)\n")
            }

            // Pregunta 2: R.id.p2_op2 -> onCreate()
            if (ans2 == R.id.p2_op2) {
                correctas++
                sb.append("✓ Pregunta 2: Correcto (onCreate())\n")
            } else {
                sb.append("✗ Pregunta 2: Incorrecto (Respuesta correcta: onCreate())\n")
            }

            // Pregunta 3: R.id.p3_op1 -> Kotlin
            if (ans3 == R.id.p3_op1) {
                correctas++
                sb.append("✓ Pregunta 3: Correcto (Kotlin)\n")
            } else {
                sb.append("✗ Pregunta 3: Incorrecto (Respuesta correcta: Kotlin)\n")
            }

            // Pregunta 4: R.id.p4_op3 -> android:id
            if (ans4 == R.id.p4_op3) {
                correctas++
                sb.append("✓ Pregunta 4: Correcto (android:id)")
            } else {
                sb.append("✗ Pregunta 4: Incorrecto (Respuesta correcta: android:id)")
            }

            val porcentaje = (correctas.toDouble() / total.toDouble()) * 100
            val mensajeNivel = when (correctas) {
                4 -> "¡Excelente puntaje perfecto! 🎉"
                3 -> "¡Buen trabajo! Tienes muy buenos conocimientos. 👍"
                2 -> "Aceptable, repasa los conceptos básicos. 📚"
                else -> "Necesitas repasar la materia. ¡Ánimo! 💡"
            }

            tvPuntaje.text = "Puntaje Final: $correctas / $total (${porcentaje.toInt()}%) - $mensajeNivel"
            tvDetalle.text = sb.toString()
            cardResultado.visibility = View.VISIBLE

            Toast.makeText(this, "Quiz evaluado exitosamente", Toast.LENGTH_SHORT).show()
        }

        btnReiniciar.setOnClickListener {
            rgP1.clearCheck()
            rgP2.clearCheck()
            rgP3.clearCheck()
            rgP4.clearCheck()
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Quiz reiniciado", Toast.LENGTH_SHORT).show()
        }
    }
}
