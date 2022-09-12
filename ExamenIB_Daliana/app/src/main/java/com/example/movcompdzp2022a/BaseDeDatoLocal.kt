package com.example.movcompdzp2022a

class BaseDeDatoLocal {

    companion object {
        var arrayPeliculas = arrayListOf<Pelicula>()
        var arrayPersonajes = arrayListOf<Personaje>()
        var arrayRelacion_Pel_Per = arrayListOf<Pelicula_Personaje>()

        init {
            //PELICULAS
            arrayPeliculas.add(Pelicula(1, "Blancanieves y los 7 enanitos",1937,1,1488423,"SI"))
            arrayPeliculas.add(Pelicula(2, "La Sirenita",1989,2,40000000,"SI"))
            arrayPeliculas.add(Pelicula(3, "La Bella y la Bestia",1991,3,25000000,"SI"))
            arrayPeliculas.add(Pelicula(4, "Cenicienta",1950,3,29000000,"SI"))

            //PERSONAJES
            arrayPersonajes.add(Personaje(1, "Blancanieves",14, "Femenino", "Blancanieves y los 7 enanitos", "SI"))
            arrayPersonajes.add(Personaje(2, "Hada Madrina",50, "Femenino", "Cenicienta", "NO"))
            arrayPersonajes.add(Personaje(3, "Principe Encantador",20, "Masculino", "Cenicienta", "NO"))
            arrayPersonajes.add(Personaje(4, "Gaston ",18, "Masculino", "La Bella y la Bestia", "NO"))
            arrayPersonajes.add(Personaje(5, "Ariel",14, "Femenino", "La Sirenita", "SI"))

            //PELICULAS-PERSONAJES
            arrayRelacion_Pel_Per.add(Pelicula_Personaje(1, "Blancanieves", 1, 1))
            arrayRelacion_Pel_Per.add(Pelicula_Personaje(2, "La Sirenita", 2, 5))
            arrayRelacion_Pel_Per.add(Pelicula_Personaje(3, "La Bella y la Bestia", 3, 4))
            arrayRelacion_Pel_Per.add(Pelicula_Personaje(4, "Cenicienta", 4, 2))
            arrayRelacion_Pel_Per.add(Pelicula_Personaje(5, "Cenicienta", 4, 3))
        }
    }
}