package com.ifgoiano.cervejaria

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var expertCerveja: ExpertCerveja

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        expertCerveja = ExpertCerveja()

        val spinnerEmpresa: Spinner = findViewById(R.id.spinner)
        val btnEncontrarCervejas: Button = findViewById(R.id.button)
        val textCervejas: TextView = findViewById(R.id.textView)

        val tiposDeCerveja = expertCerveja.obterTiposDeCerveja()
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposDeCerveja)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmpresa.adapter = spinnerAdapter

        btnEncontrarCervejas.setOnClickListener {
            val tipoSelecionado = spinnerEmpresa.selectedItem.toString()
            val cervejas = expertCerveja.obterCervejasPorTipo(tipoSelecionado)?.joinToString("\n")
            textCervejas.text = cervejas ?: "Nenhuma cerveja encontrada"
        }
    }
}