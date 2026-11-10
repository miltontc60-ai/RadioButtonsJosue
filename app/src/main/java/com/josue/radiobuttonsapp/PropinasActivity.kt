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
import java.util.Locale

/**
 * Ejercicio 4: Calculadora de Propinas
 * Desarrollado por: Josué
 * Calcula el valor de la propina y el total a pagar según el porcentaje seleccionado con RadioButtons (10%, 15%, 20%).
 */
class PropinasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_propinas)

        val btnVolver = findViewById<Button>(R.id.btnVolverPropinas)
        val etMonto = findViewById<TextInputEditText>(R.id.etMontoCuenta)
        val rgPropina = findViewById<RadioGroup>(R.id.rgPorcentajePropina)
        val btnCalcular = findViewById<Button>(R.id.btnCalcularPropina)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiarPropina)

        val cardResultado = findViewById<MaterialCardView>(R.id.cardResultadoPropina)
        val tvMontoBase = findViewById<TextView>(R.id.tvMontoBaseRes)
        val tvEtiquetaPorcentaje = findViewById<TextView>(R.id.tvEtiquetaPorcentaje)
        val tvMontoPropina = findViewById<TextView>(R.id.tvMontoPropinaRes)
        val tvTotalPagar = findViewById<TextView>(R.id.tvTotalPagarRes)

        btnVolver.setOnClickListener {
            finish()
        }

        btnCalcular.setOnClickListener {
            val montoTexto = etMonto.text?.toString()?.trim()

            if (montoTexto.isNullOrEmpty()) {
                etMonto.error = "Ingrese el valor de la cuenta"
                Toast.makeText(this, "Por favor ingrese el monto consumido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val montoBase = montoTexto.toDoubleOrNull()
            if (montoBase == null || montoBase <= 0.0) {
                etMonto.error = "Ingrese un monto mayor a 0"
                Toast.makeText(this, "El monto debe ser un número positivo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val porcentaje = when (rgPropina.checkedRadioButtonId) {
                R.id.rbPropina10 -> 10
                R.id.rbPropina15 -> 15
                R.id.rbPropina20 -> 20
                else -> 15
            }

            val propinaCalculada = montoBase * (porcentaje / 100.0)
            val totalFinal = montoBase + propinaCalculada

            tvMontoBase.text = String.format(Locale.US, "$%.2f", montoBase)
            tvEtiquetaPorcentaje.text = "Propina ($porcentaje%):"
            tvMontoPropina.text = String.format(Locale.US, "$%.2f", propinaCalculada)
            tvTotalPagar.text = String.format(Locale.US, "$%.2f", totalFinal)

            cardResultado.visibility = View.VISIBLE
            Toast.makeText(this, "Cálculo realizado con éxito", Toast.LENGTH_SHORT).show()
        }

        btnLimpiar.setOnClickListener {
            etMonto.text?.clear()
            etMonto.error = null
            findViewById<RadioGroup>(R.id.rgPorcentajePropina).check(R.id.rbPropina15)
            cardResultado.visibility = View.GONE
            Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
        }
    }
}
