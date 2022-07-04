import java.io.File
import java.util.ArrayList
import java.util.Date

class Pelicula {
    val separador = ","
    val salto_linea = "\n"
    var id_peliculas: String = ""
    var nombre_pelicula: String = ""
    var anio_creacion: Int = 0
    var numero_sagas: Int = 0
    var presupuesto: Float = 0.0f
    var exito_Mercado: String = ""

    init {
        println("Ingrese un ID para la pelicula (Ej: P10)")
        id_peliculas = id_peliculas_teclado()

        println("Ingrese el nombre de la pelicula")
        nombre_pelicula = nombre_pelicula_teclado()

        println("Ingrese el anioo de creacion de la pelicula")
        anio_creacion = anio_creacion_teclado()

        println("Ingrese el numero de sagas que tuvo la pelicula")
        numero_sagas = numero_sagas_teclado()

        println("Ingrese el presupuesto con el que conto de la pelicula de Disney")
        presupuesto = presupuesto_teclado()

        println("La pelicula fue un exito? (Ej: SI/NO)")
        exito_Mercado = exito_Mercado_teclado()

    }

    fun registrarPelicula(nombreArchivo: String){
        File(nombreArchivo).appendText("$salto_linea$id_peliculas$separador$nombre_pelicula$separador$anio_creacion$separador$numero_sagas$separador$presupuesto$separador$exito_Mercado")
    }
}