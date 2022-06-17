package com.example.trabajoenclases02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Código
        val contador = 0
        val btnAumentar = findViewById<Button>(R.id.btn_Aumentar)
        val contadorView = findViewById<TextView>(R.id.tV_contador)

        btnAumentar
            .setOnClickListener {
                aumentarContador(contador)
            }
    }

fun aumentarContador (
    contador: Int,
): Int{
    return contador + 1
}

}