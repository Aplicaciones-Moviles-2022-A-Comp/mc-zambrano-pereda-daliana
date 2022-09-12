package com.example.movcompdzp2022a

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.movcompdzp2022a.ui.home.HomeFragment

class AgregarNota : AppCompatActivity() {

    private lateinit var noteEditTitle: EditText
    private lateinit var noteEditDescripcion: EditText
    private lateinit var btnGuardar: Button
    private lateinit var notasFirebase: NotasFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        var tituloI = findViewById<EditText>(R.id.editTextTextPersonName2)
        var descripcionI = findViewById<EditText>(R.id.editTextTextPersonName3)
        notasFirebase = NotasFirebase()

        val btnAtras = findViewById<ImageView>(R.id.imageView6)
        btnAtras.setOnClickListener {
            val intent = Intent (this, MenuIzquierdo::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }


        val btnGuardar = findViewById<Button>(R.id.button)
        btnGuardar.setOnClickListener {
            val verificacion = tituloI.text.toString()
            val descripcion = descripcionI.text.toString()
            if (verificacion.isNotEmpty()){
                notasFirebase.agregarNota(verificacion, descripcion)
                val intent2 = Intent (this, HomeFragment::class.java)
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent2)
            }

        }
    }
}