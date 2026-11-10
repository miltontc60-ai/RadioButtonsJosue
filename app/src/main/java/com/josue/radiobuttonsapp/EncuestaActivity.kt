package com.josue.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText

/**
 * Ejercicio 1: Encuesta de Satisfacción
 * Desarrollado por: Josué
 * Permite calificar un servicio mediante RadioButtons (Excelente, Bueno, Regular, Malo)
 * y muestra un resumen detallado con retroalimentación personalizada.
 */
class EncuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val rgCalificacion = findViewById<RadioGroup>(R.id.rgCalificacion)
        val etComentario = findViewById<TextInputEditText>(R.id.etComentario)
        val btnEnviar = findViewById<Button>(R.id.btnEnviarEncuesta)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiarEncuesta)

        val cardResumen = findViewById<MaterialCardView>(R.id.cardResumen)
        val tvResultadoCalificacion = findViewById<TextView>(R.id.tvResultadoCalificacion)
        val tvMensajeRetroalimentacion = findViewById<TextView>(R.id.tvMensajeRetroalimentacion)
        val tvDetalleComentario = findViewById<TextView>(R.id.tvDetalleComentario)

        btnVolver.setOnClickListener {
            finish()
        }

        btnEnviar.setOnClickListener {
            val selectedId = rgCalificacion.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Por favor seleccione una calificación", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val calificacion: String
            val mensaje: String

            when (selectedId) {
                R.id.rbExcelente -> {
                    calificacion = "Excelente (⭐⭐⭐⭐⭐)"
                    mensaje = "¡Extraordinario! Muchas gracias por su valoración positiva. Nos impulsa a seguir manteniendo la excelencia."
                }
                R.id.rbBueno -> {
                    calificacion = "Bueno (⭐⭐⭐⭐)"
                    mensaje = "¡Muchas gracias! Nos alegra que haya tenido una experiencia agradable con nosotros."
                }
                R.id.rbRegular -> {
                    calificacion = "Regular (⭐⭐⭐)"
                    mensaje = "Agradecemos sus comentarios. Tomaremos nota para mejorar los aspectos necesarios en nuestro servicio."
                }
                R.id.rbMalo -> {
                    calificacion = "Malo (⭐)"
                    mensaje = "Lamentamos profundamente su mala experiencia. Nos pondremos en contacto para resolver su inconveniente."
                }
                else -> {
                    calificacion = "No especificado"
                    mensaje = ""
                }
            }

            val comentarios = etComentario.text?.toString()?.trim()

            tvResultadoCalificacion.text = "Calificación registrada: $calificacion"
            tvMensajeRetroalimentacion.text = mensaje

            if (!comentarios.isNullOrEmpty()) {
                tvDetalleComentario.text = "Observaciones: \"$comentarios\""
                tvDetalleComentario.visibility = View.VISIBLE
            } else {
                tvDetalleComentario.text = "Observaciones: Sin comentarios adicionales."
                tvDetalleComentario.visibility = View.VISIBLE
            }

            cardResumen.visibility = View.VISIBLE
            Toast.makeText(this, "¡Encuesta registrada exitosamente!", Toast.LENGTH_SHORT).show()
        }

        btnLimpiar.setOnClickListener {
            rgCalificacion.clearCheck()
            etComentario.text?.clear()
            cardResumen.visibility = View.GONE
            Toast.makeText(this, "Formulario restablecido", Toast.LENGTH_SHORT).show()
        }
    }
}
