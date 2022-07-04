import java.io.File

class Personaje (){
    val separador = ","
    val salto_linea = "\n"
    var id_personaje: String = ""
    var nombre_personaje: String = ""
    var edad: Int = 0
    var genero: String = ""
    var nombre_pelicula: String = ""
    var es_princesa: String = ""

    init {
        println("Ingrese un ID para el personaje (Ej: D10)")
        id_personaje = id_teclado()

        println("Ingrese el nombre del personaje")
        nombre_personaje = nombre_personaje_teclado()

        println("Ingrese la edad del personaje")
        edad = edad_teclado()

        println("Ingrese el 1er caracter del genero del personaje (Ej: F/M)")
        genero = genero_teclado()

        println("Ingrese el nombre de la pelicula de Disney de donde es el personaje")
        nombre_pelicula = nombre_pelicula_teclado()

        println("El personaje es una princesa? (Ej: SI/NO)")
        es_princesa = es_princesa_teclado()

    }

    fun registrarPersonaje(nombreArchivo: String){
        File(nombreArchivo).appendText("$salto_linea$id_personaje$separador$nombre_personaje$separador$edad$separador$genero$separador$nombre_pelicula$separador$es_princesa")
    }
}