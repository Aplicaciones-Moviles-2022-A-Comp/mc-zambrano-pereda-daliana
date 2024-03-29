package com.example.movcompdzp2022a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.AdapterView
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.ArrayAdapter as ArrayAdapter1

class ListaPersonajes : AppCompatActivity() {

    var personajeSeleccionado = 0
    var idPelicula = 0
    var peliculaPosicion = 0
    var itemS = 0

    var listaPersonajes = arrayListOf<String>()
    var idRelacion_Pel_Per = arrayListOf<Int>()

    var resultAnadirPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                peliculaPosicion = data?.getIntExtra("posicionPelicula",0)!!
            }
        }

    }

    var resultEditarPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                peliculaPosicion = data?.getIntExtra("posicionPelicula",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personajes)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        listaPersonajes = arrayListOf()
        idRelacion_Pel_Per = arrayListOf()
        peliculaPosicion = intent.getIntExtra("posicionEditar",1)



        val nombreRelacion = findViewById<TextView>(R.id.tv_Pelicula)

        BaseDeDatoLocal.arrayPeliculas.forEachIndexed{ indice: Int, pelicula : Pelicula ->
            if (indice == peliculaPosicion){
                idPelicula = pelicula.idPelicula
                var label = "Pelicula: ${pelicula.nombrePelicula}"
                nombreRelacion.setText(label)
            }
        }

        BaseDeDatoLocal.arrayRelacion_Pel_Per.forEachIndexed{ indice: Int, pelicula_personaje : Pelicula_Personaje ->
            if (idPelicula == pelicula_personaje.idPelicula){
                listaPersonajes.add(pelicula_personaje.nombreRelacion.toString())
                idRelacion_Pel_Per.add(pelicula_personaje.idRelacion_Pelicula_Personaje)
            }
        }

        val listaPersonajes = findViewById<ListView>(R.id.lv_personaje_lista)

        val adaptador = ArrayAdapter1(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatoLocal.arrayPersonajes
        )
        listaPersonajes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearPersonaje = findViewById<Button>(R.id.bt_CrearPersonaje)
        btnCrearPersonaje.setOnClickListener {
            abrirActividadAddPersonaje(CrearPersonaje::class.java)
        }

        this.registerForContextMenu(listaPersonajes)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_peliculas, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemS = id
        personajeSeleccionado = idRelacion_Pel_Per.elementAt(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarPersonaje -> {
                Log.i("context-menu", "Edit position: ${personajeSeleccionado}")
                abrirActividadEditarPersonaje(Editar_Personaje::class.java)
                return true
            }
            R.id.mi_eliminarPersonaje -> {
                Log.i("context-menu", "Delete position: ${personajeSeleccionado}")
                eliminarJugador(personajeSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarPersonaje(
        clase: Class<*>
    ) {
        val intentEditarJugador = Intent(this, clase)
        intentEditarJugador.putExtra("personaje", personajeSeleccionado)
        intentEditarJugador.putExtra("posicionPeliculaeditar",peliculaPosicion)
        resultEditarPersonaje.launch(intentEditarJugador)
    }

    fun abrirActividadAddPersonaje(
        clase: Class<*>
    ) {
        val intentAddNewJugador = Intent(this, clase)
        intentAddNewJugador.putExtra("posicionPelicula",peliculaPosicion)
        resultAnadirPersonaje.launch(intentAddNewJugador)
    }

    fun eliminarJugador(
        idJugadorAeliminar: Int
    ){
        val listaPersonaje = findViewById<ListView>(R.id.lv_personaje_lista)

        var auxRelacion_Pel_Per = arrayListOf<Pelicula_Personaje>()

        BaseDeDatoLocal.arrayRelacion_Pel_Per.forEach{ pelicula_personaje:Pelicula_Personaje ->
            if(idJugadorAeliminar != pelicula_personaje.idRelacion_Pelicula_Personaje){
                auxRelacion_Pel_Per.add(pelicula_personaje)
            }
        }

        BaseDeDatoLocal.arrayRelacion_Pel_Per = auxRelacion_Pel_Per

        listaPersonajes.removeAt(itemS)

        val adaptador = ArrayAdapter1(
            this,
            android.R.layout.simple_list_item_1,
            listaPersonajes
        )
        listaPersonaje.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}