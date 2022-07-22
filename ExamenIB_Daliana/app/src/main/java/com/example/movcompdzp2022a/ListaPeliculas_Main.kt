package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class ListaPeliculas_Main : AppCompatActivity() {

    var peliculaSeleccionada = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas_main)
    }

    override fun onStart() {
        super.onStart()

        val listaPeliculas = findViewById<ListView>(R.id.lv_peliculas_lista)

        val adaptador = ArrayAdapter (
            this,
            android.R.layout.simple_list_item_1,
            CompanionObjecto.arrayPeliculas
        )

        listaPeliculas.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listaPeliculas)

        val btnCrearPelicula = findViewById<Button>(R.id.bt_CrearPelicula)
        btnCrearPelicula.setOnClickListener {
            val intent = Intent (this, CrearPelicula::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("peliculaSeleccionada",peliculaSeleccionada)
            putParcelableArrayList("arrayPeliculas",CompanionObjecto.arrayPeliculas)
            putParcelableArrayList("arrayRelacion_Pel_Per",CompanionObjecto.arrayRelacion_Pel_Per)
            putParcelableArrayList("arrayPersonajes",CompanionObjecto.arrayPersonajes)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        peliculaSeleccionada = savedInstanceState.getInt("peliculaSeleccionada")
        CompanionObjecto.arrayPeliculas = savedInstanceState.getParcelableArrayList<Pelicula>("arrayPeliculas")!!
        CompanionObjecto.arrayRelacion_Pel_Per = savedInstanceState.getParcelableArrayList<Pelicula_Personaje>("arrayRelacion_Pel_Per")!!
        CompanionObjecto.arrayPersonajes = savedInstanceState.getParcelableArrayList<Personaje>("arrayPersonajes")!!
        if (peliculaSeleccionada == null){
            peliculaSeleccionada = 0
        }
        val listaPeliculas = findViewById<ListView>(R.id.lv_peliculas_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            CompanionObjecto.arrayPeliculas
        )
        listaPeliculas.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        peliculaSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${peliculaSeleccionada}")
                abrirActividadConParametros(Editar_Pelicula::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${peliculaSeleccionada}")
                eliminarPelicula(peliculaSeleccionada)
                return true
            }
            R.id.mi_personaje -> {
                Log.i("context-menu", "Pokemons: ${peliculaSeleccionada}")
                abrirActividadConParametros(ListaPersonajes::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarPelicula = Intent(this, clase)
        intentEditarPelicula.putExtra("posicionEditar", peliculaSeleccionada)
        startActivity(intentEditarPelicula)
    }

    fun eliminarPelicula(
        posicionPeliculaAeliminar: Int
    ) {
        val listaPelicula = findViewById<ListView>(R.id.lv_peliculas_lista)

        var peliculaAeliminar = CompanionObjecto.arrayPeliculas.elementAt(posicionPeliculaAeliminar)
        var idPeliculaAeliminar = peliculaAeliminar.idPelicula

        var listaAuxPersonajes = arrayListOf<Pelicula_Personaje>()

        CompanionObjecto.arrayRelacion_Pel_Per.forEachIndexed{ indice: Int, pelicula_personaje: Pelicula_Personaje ->
            if(idPeliculaAeliminar != pelicula_personaje.idPelicula){
                listaAuxPersonajes.add(pelicula_personaje)
            }
        }

        CompanionObjecto.arrayPeliculas.removeAt(posicionPeliculaAeliminar) //
        CompanionObjecto.arrayRelacion_Pel_Per = listaAuxPersonajes //

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            CompanionObjecto.arrayPeliculas
        )
        listaPelicula.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}