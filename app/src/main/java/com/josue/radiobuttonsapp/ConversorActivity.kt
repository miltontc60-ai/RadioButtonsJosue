package com.josue.radiobuttonsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

/**
 * Ejercicio 5: Conversor de Unidades
 * Desarrollado por: Josué
 * Permite seleccionar el tipo de conversión (longitud, peso, temperatura) mediante RadioButtons,
 * adaptando los campos de entrada y calculando la conversión correspondiente.
 */
class ConversorActivity : AppCompatActivity() {

    private lateinit var tilValor: TextInputLayout
    private lateinit var etValor: TextInputEditText
    private lateinit var rgMagnitud: RadioGroup
    private lateinit var rgSubOpciones: RadioGroup
    private lateinit var rbSub1: RadioButton
    private lateinit var rbSub2: RadioButton
    private lateinit var rbSub3: RadioButton
    private lateinit var cardResultado: MaterialCardView
    private lateinit var tvValorConvertido: TextView
    private lateinit var tvFormulaAplicada: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        val btnVolver = findViewById<Button>(R.id.btnVolverConversor)
        tilValor = findViewById(R.id.tilValorEntrada)
        etValor = findViewById(R.id.etValorEntrada)
        rgMagnitud = findViewById(R.id.rgTipoMagnitud)
        rgSubOpciones = findViewById(R.id.rgSubOpciones)
        rbSub1 = findViewById(R.id.rbSubOpt1)
        rbSub2 = findViewById(R.id.rbSubOpt2)
        rbSub3 = findViewById(R.id.rbSubOpt3)

        val btnConvertir = findViewById<Button>(R.id.btnConvertir)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiarConversor)
        cardResultado = findViewById(R.id.cardResultadoConversor)
        tvValorConvertido = findViewById(R.id.tvValorConvertido)
        tvFormulaAplicada = findViewById(R.id.tvFormulaAplicada)

        btnVolver.setOnClickListener {
            finish()
        }

        // Listener para adaptar dinámicamente los campos según el tipo de magnitud seleccionado
        rgMagnitud.setOnCheckedChangeListener { _, checkedId ->
            adaptarCamposSegunMagnitud(checkedId)
            cardResultado.visibility = View.GONE
        }

        btnConvertir.setOnClickListener {
            realizarConversion()
        }

        btnLimpiar.setOnClickListener {
            etValor.text?.clear()
            etValor.error = null
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Campos restablecidos", Toast.LENGTH_SHORT).show()
        }

        // Configuración inicial por defecto (Longitud)
        adaptarCamposSegunMagnitud(R.id.rbLongitud)
    }

    private fun adaptarCamposSegunMagnitud(magnitudId: Int) {
        when (magnitudId) {
            R.id.rbLongitud -> {
                tilValor.hint = "Ingrese longitud en Metros (m)"
                rbSub1.text = "Metros (m) a Kilómetros (km)"
                rbSub2.text = "Metros (m) a Millas (mi)"
                rbSub3.text = "Metros (m) a Pies (ft)"
                rbSub1.isChecked = true
            }
            R.id.rbPeso -> {
                tilValor.hint = "Ingrese peso en Kilogramos (kg)"
                rbSub1.text = "Kilogramos (kg) a Libras (lb)"
                rbSub2.text = "Kilogramos (kg) a Gramos (g)"
                rbSub3.text = "Kilogramos (kg) a Onzas (oz)"
                rbSub1.isChecked = true
            }
            R.id.rbTemperatura -> {
                tilValor.hint = "Ingrese valor de temperatura"
                rbSub1.text = "Celsius (°C) a Fahrenheit (°F)"
                rbSub2.text = "Fahrenheit (°F) a Celsius (°C)"
                rbSub3.text = "Celsius (°C) a Kelvin (K)"
                rbSub1.isChecked = true
            }
        }
    }

    private fun realizarConversion() {
        val textoEntrada = etValor.text?.toString()?.trim()

        if (textoEntrada.isNullOrEmpty()) {
            etValor.error = "Ingrese un valor numérico"
            Toast.makeText(this, "Por favor ingrese una cantidad a convertir", Toast.LENGTH_SHORT).show()
            return
        }

        val valor = textoEntrada.toDoubleOrNull()
        if (valor == null) {
            etValor.error = "Formato numérico no válido"
            return
        }

        val magnitudId = rgMagnitud.checkedRadioButtonId
        val subId = rgSubOpciones.checkedRadioButtonId

        var resultado = 0.0
        var resultadoStr = ""
        var formulaStr = ""

        when (magnitudId) {
            R.id.rbLongitud -> {
                if (valor < 0) {
                    etValor.error = "La longitud no puede ser negativa"
                    return
                }
                when (subId) {
                    R.id.rbSubOpt1 -> {
                        resultado = valor / 1000.0
                        resultadoStr = String.format(Locale.US, "%.2f m = %.4f km", valor, resultado)
                        formulaStr = "Fórmula: Valor en metros / 1000"
                    }
                    R.id.rbSubOpt2 -> {
                        resultado = valor * 0.000621371
                        resultadoStr = String.format(Locale.US, "%.2f m = %.4f mi", valor, resultado)
                        formulaStr = "Fórmula: Valor en metros × 0.000621371"
                    }
                    R.id.rbSubOpt3 -> {
                        resultado = valor * 3.28084
                        resultadoStr = String.format(Locale.US, "%.2f m = %.2f ft", valor, resultado)
                        formulaStr = "Fórmula: Valor en metros × 3.28084"
                    }
                }
            }
            R.id.rbPeso -> {
                if (valor < 0) {
                    etValor.error = "El peso no puede ser negativo"
                    return
                }
                when (subId) {
                    R.id.rbSubOpt1 -> {
                        resultado = valor * 2.20462
                        resultadoStr = String.format(Locale.US, "%.2f kg = %.2f lb", valor, resultado)
                        formulaStr = "Fórmula: Valor en kg × 2.20462"
                    }
                    R.id.rbSubOpt2 -> {
                        resultado = valor * 1000.0
                        resultadoStr = String.format(Locale.US, "%.2f kg = %.2f g", valor, resultado)
                        formulaStr = "Fórmula: Valor en kg × 1000"
                    }
                    R.id.rbSubOpt3 -> {
                        resultado = valor * 35.274
                        resultadoStr = String.format(Locale.US, "%.2f kg = %.2f oz", valor, resultado)
                        formulaStr = "Fórmula: Valor en kg × 35.274"
                    }
                }
            }
            R.id.rbTemperatura -> {
                when (subId) {
                    R.id.rbSubOpt1 -> {
                        resultado = (valor * 9.0 / 5.0) + 32.0
                        resultadoStr = String.format(Locale.US, "%.2f °C = %.2f °F", valor, resultado)
                        formulaStr = "Fórmula: (°C × 9/5) + 32 = °F"
                    }
                    R.id.rbSubOpt2 -> {
                        resultado = (valor - 32.0) * 5.0 / 9.0
                        resultadoStr = String.format(Locale.US, "%.2f °F = %.2f °C", valor, resultado)
                        formulaStr = "Fórmula: (°F - 32) × 5/9 = °C"
                    }
                    R.id.rbSubOpt3 -> {
                        resultado = valor + 273.15
                        resultadoStr = String.format(Locale.US, "%.2f °C = %.2f K", valor, resultado)
                        formulaStr = "Fórmula: °C + 273.15 = K"
                    }
                }
            }
        }

        tvValorConvertido.text = resultadoStr
        tvFormulaAplicada.text = formulaStr
        cardResultado.visibility = View.VISIBLE
        Toast.makeText(this, "Conversión completada", Toast.LENGTH_SHORT).show()
    }
}
