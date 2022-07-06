package com.example.movcompdzp2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Código
        val btnAumentar = findViewById<Button>(R.id.btn_Aumentar)
        val contadorView = findViewById<TextView>(R.id.tV_contador)

        btnAumentar.setOnClickListener {
            contador ++
            contadorView.text = contador.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("contadorGuardado", contador)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val contadorRecuperado:Int? = savedInstanceState.getInt("contadorGuardado")
        if (contadorRecuperado!= null) {
           contador = contadorRecuperado
        }
    }

    override fun onResume() {
        super.onResume()
        val contadorSinModificar = findViewById<TextView>(R.id.tV_contador)
        contadorSinModificar.text = contador.toString()
    }
}