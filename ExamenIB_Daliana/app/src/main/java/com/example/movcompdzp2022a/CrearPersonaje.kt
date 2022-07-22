package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CrearPersonaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)

        val btnCancelar = findViewById<Button>(R.id.btn_cancelarPerC)
        btnCancelar.setOnClickListener {
            val intent = Intent (this, ListaPersonajes::class.java)
            startActivity(intent)
        }
    }
}