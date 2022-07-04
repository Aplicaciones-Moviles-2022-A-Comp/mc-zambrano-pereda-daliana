import java.io.File
import java.io.PrintWriter
import java.util.ArrayList
import java.util.Scanner
import kotlin.system.exitProcess

//Variable globales
val path_personajes = "C:/Users/daliana.zambrano/Documents/Github/mc-zambrano-pereda-daliana/000-Deberes/Deber_01_CRUD/src/archivos/personajes.txt"
val path_peliculas = "C:/Users/daliana.zambrano/Documents/Github/mc-zambrano-pereda-daliana/000-Deberes/Deber_01_CRUD/src/archivos/peliculas.txt"
val reader = Scanner(System.`in`)

fun main(args : Array<String>) {
    println("***************************************************** \n" +
            "********         DEBER 01 - CRUD             ******** \n" +
            "********  PELICULAS DE DISNEY / PERSONAJES   ******** \n" +
            "********       Daliana Zambrano Pereda       ******** ")
    menu()
}

fun menu () {
    println(
        "*****************************************************\n"+
        "\nOpciones del menu: \n" +
                " 1. Insertar \n" +
                " 2. Ver registros \n" +
                " 3. Actualizar \n" +
                " 4. Borrar \n" +
                " 5. Salir"
    )

    println("Ingrese el numero de la opcion que desea realizar: ")
    var inputTeclado = reader.nextInt()

    when (inputTeclado) {
        1 -> menuInsertar()
        2 -> menuLeer()
        3 -> menuActualizar()
        4 -> menuBorrar()
        5 -> salir()
    }
}

//Funciones CRUD
    fun menuInsertar () {
        println("*****************************************************")
        println("Desea insertar una pelicula de Disney o un personaje?")
        println(" 1. Personaje")
        println(" 2. Pelicula")
        println("Ingrese el numero de la opcion: ")
        var inputInsertar = reader.nextInt()
        when (inputInsertar) {
            1 -> {
                println("*****************************************************")
                println("Desea ver los personajes existentes? SI o NO")
                var inputListarPersonajes = readLine()!!
                if (inputListarPersonajes.equals("SI") || inputListarPersonajes.equals("Si") || inputListarPersonajes.equals("si"))
                {
                    leerArchivo(path_personajes, "PERSONAJES")
                    println("*****************************************************\n")
                    println("Llene la informacion que se le pide: ")
                    var personaje = Personaje()
                    personaje.registrarPersonaje(path_personajes)
                    println("Personaje registrado exitosamente!\n")
                    menu()
                } else if (inputListarPersonajes.equals("NO") || inputListarPersonajes.equals("No") || inputListarPersonajes.equals("no"))
                {
                    var personaje = Personaje()
                    personaje.registrarPersonaje(path_personajes)
                    println("Personaje registrado exitosamente!\n")
                    menu()
                } else {
                    println("Opcion incorrecta")
                    menu()
                }
            }
            2 -> {
                println("*****************************************************")
                println("Desea ver las peliculas existentes? SI o NO")
                var inputListarPeliculas = readLine()!!
                if (inputListarPeliculas.equals("SI") || inputListarPeliculas.equals("Si") || inputListarPeliculas.equals(
                        "si"
                    )
                ) {
                    leerArchivo(path_peliculas, "PELICULAS")
                    println("*****************************************************\n")
                    println("Llene la informacion que se le pide: ")
                    var pelicula = Pelicula()
                    pelicula.registrarPelicula(path_peliculas)
                    println("Pelicula registrada exitosamente!\n")
                    menu()
                } else if (inputListarPeliculas.equals("NO") || inputListarPeliculas.equals("No") || inputListarPeliculas.equals(
                        "no"
                    )
                ) {
                    var pelicula = Pelicula()
                    pelicula.registrarPelicula(path_peliculas)
                    println("Personaje registrado exitosamente!\n")
                    menu()
                } else {
                    println("Opcion incorrecta")
                    menu()
                }
            }
        }
    }

    fun menuLeer () {
        println("*****************************************************")
        println("Que desea ver?")
        println(" 1. Personajes")
        println(" 2. Peliculas")
        println(" 3. Personajes y peliculas")
        println(" 4. Volver al menu principal")
        println("Ingrese el numero de la opcion: ")
        var inputLeer = reader.nextInt()

        when (inputLeer) {
            1 -> {
                leerArchivo(path_personajes, "PERSONAJES")
                verMasOpciones()
            }
            2 -> {
                leerArchivo(path_peliculas, "PELICULAS")
                verMasOpciones()
            }
            3 -> {
                leerArchivo(path_personajes, "PERSONAJES")
                println("\n")
                leerArchivo(path_peliculas, "PELICULAS")
                verMasOpciones()
            }
            4 -> {
                menu()
            }
        }
    }

    fun menuActualizar () {
            println("*****************************************************")
            println("Que desea actualizar?")
            println(" 1. Un personaje")
            println(" 2. Una pelicula")
            println(" 3. Volver al menu principal")
            println("Ingrese el numero de la opcion: ")
            var inputActualizar = reader.nextInt()
            when (inputActualizar) {
                1 -> {
                    actualizarPersonaje()
                    menu()
                }
                2 -> {
                    actualizarPelicula()
                    menu()
                }
                3 -> {
                    menu()
                }
            }
        }

    fun actualizarPersonaje () {
        leerArchivo(path_personajes, "PERSONAJES")
        println("*****************************************************\n" +
                "Ingrese el ID del personaje que desea actualizar")
        val id_busqueda = readLine()!!

        if (buscarRegistro(id_busqueda, path_personajes)) {
            println(
                "*****************************************************\n" +
                        "Que dato desea actualizar?:\n" +
                        " 1. El ID\n" +
                        " 2. El nombre del personaje\n" +
                        " 3. La edad\n" +
                        " 4. El genero\n" +
                        " 5. El nombre de la pelicula\n" +
                        " 6. Si es princesa o no")
            val dato_a_actualizar = reader.nextInt()
            var nuevo_ingreso_dato = ""

            when (dato_a_actualizar) {
                1 -> {
                    println("Ingrese ID corregido del personaje: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                2 -> {
                    println("Ingrese el nombre corregido del personaje: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                3 -> {
                    println("Ingrese la edad corregida del personaje: ")
                    nuevo_ingreso_dato = reader.nextInt().toString()
                }
                4 -> {
                    println("Ingrese el genero corregido del personaje (F/M): ")
                    nuevo_ingreso_dato = readLine()!!
                }
                5 -> {
                    println("Ingrese nombre corregido de la pelicula: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                6 -> {
                    println("Ingrese si el personaje es o no princesa (SI/NO): ")
                    nuevo_ingreso_dato = readLine()!!
                }
            }
            actualizarRegistro(id_busqueda,dato_a_actualizar, nuevo_ingreso_dato, path_personajes)
        }else {
            println("Opcion no valida")
            actualizarPersonaje()
        }
    }

    fun actualizarPelicula () {
        leerArchivo(path_peliculas, "PELICULAS")
        println("*****************************************************\n" +
                "Ingrese el ID de la pelicula que desea actualizar")
        val id_busqueda = readLine()!!

        if (buscarRegistro(id_busqueda, path_peliculas)) {
            println(
                "*****************************************************\n" +
                        "Que dato desea actualizar?:\n" +
                        " 1. El ID\n" +
                        " 2. El nombre de la pelicula\n" +
                        " 3. El anio de creacion\n" +
                        " 4. El numero de sagas\n" +
                        " 5. El presupuesto\n" +
                        " 6. Si tuvo exito en el mercado o no")
            val dato_a_actualizar = reader.nextInt()
            var nuevo_ingreso_dato = ""

            when (dato_a_actualizar) {
                1 -> {
                    println("Ingrese ID corregido de la pelicula: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                2 -> {
                    println("Ingrese el nombre corregido de la pelicula: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                3 -> {
                    println("Ingrese el anio de creacion corregido de la pelicula: ")
                    nuevo_ingreso_dato = reader.nextInt().toString()
                }
                4 -> {
                    println("Ingrese el el numero de sagas corregido de la pelicula: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                5 -> {
                    println("Ingrese el presupuesto corregido de la pelicula: ")
                    nuevo_ingreso_dato = readLine()!!
                }
                6 -> {
                    println("Ingrese si de la pelicula tuvo o no exito en el mercado ")
                    nuevo_ingreso_dato = readLine()!!
                }
            }
            actualizarRegistro(id_busqueda,dato_a_actualizar, nuevo_ingreso_dato, path_peliculas)
        }else {
            println("Opcion no valida")
            actualizarPelicula()
        }
    }

    fun menuBorrar () {
        println("*****************************************************")
        println("Que desea borrar?")
        println(" 1. Un personaje")
        println(" 2. Una pelicula")
        println(" 3. Volver al menu principal")
        println("Ingrese el numero de la opcion: ")
        var inputBorrar = reader.nextInt()
        when (inputBorrar) {
            1 -> {
                borrarPersonaje()
                menu()
            }
            2 -> {
                borrarPelicula()
                menu()
            }
            3 -> {
                menu()
            }
        }
    }

    fun borrarPersonaje () {
        leerArchivo(path_personajes, "PERSONAJES")
        println("*****************************************************\n" +
                "Ingrese el ID del personaje que desea borrar")
        val id_busqueda = readLine()!!

        if (buscarRegistro(id_busqueda, path_personajes)) {
            eliminarRegistro(id_busqueda, path_personajes)
        }else {
            println("Opcion no valida")
            borrarPersonaje()
        }
    }

    fun borrarPelicula () {
        leerArchivo(path_peliculas, "PELICULAS")
        println("*****************************************************\n" +
                "Ingrese el ID de la pelicula que desea borrar")
        val id_busqueda = readLine()!!

        if (buscarRegistro(id_busqueda, path_peliculas)) {
            eliminarRegistro(id_busqueda, path_peliculas)
        }else {
            println("Opcion no valida")
            borrarPelicula()
        }
    }

    fun salir () {
        exitProcess(0)
    }


//Funciones de tratamiento de archivos
    fun leerArchivo(nombreArchivo: String, tituloArchivo: String) {
        println("********************* $tituloArchivo *********************")
        val lines: List<String> = File(nombreArchivo).readLines()
        lines.forEach { line -> println(line) }
    }

    fun convertirArchivoEnLista(nombreArchivo: String): ArrayList<MutableList<String>> {
        var lista_archivo = ArrayList<MutableList<String>>()
        File(nombreArchivo).forEachLine { lista_archivo.add(it.split(",") as MutableList<String>) }
        return lista_archivo
    }

    fun buscarRegistro(id: String, nombreArchivo: String): Boolean {
        val registros = convertirArchivoEnLista(nombreArchivo)
        return registros.any { it[0].equals(id, ignoreCase = true) }
    }

    fun actualizarRegistro(id_busqueda: String, dato_a_actualizar: Int, nuevo_ingreso_dato: String, nombreArchivo: String) {
        val registro = convertirArchivoEnLista(nombreArchivo)
        registro.forEach { if (it[0].equals(id_busqueda, true)) it[dato_a_actualizar-1] = nuevo_ingreso_dato }
        sobreescribirArchivo(registro, nombreArchivo)
        println("Registro actualizado exitosamente!")
    }

    fun sobreescribirArchivo(registro: ArrayList<MutableList<String>>, nombreArchivo: String) {
        val writer = PrintWriter(nombreArchivo)
        registro.forEach { itList ->
            itList.forEach { if (it == itList[itList.size - 1]) writer.append("$it\n") else writer.append("$it,") }
        }
        writer.close()
    }

    fun eliminarRegistro(id_busqueda: String, nombreArchivo: String) {
        var registro = convertirArchivoEnLista(nombreArchivo)
        registro = registro.filter { !(it[0].equals(id_busqueda, true)) } as ArrayList<MutableList<String>>
        sobreescribirArchivo(registro, nombreArchivo)
        println("Registro elimindo exitosamente!")
    }

    fun verMasOpciones () {
        println("\n*****************************************************")
        println("Desea ver algo mas? SI/NO")
        var inputVerMas = readLine()!!
        if (inputVerMas.equals("SI") || inputVerMas.equals("Si") || inputVerMas.equals("si")){
            menuLeer()
        }else if (inputVerMas.equals("NO") || inputVerMas.equals("No") || inputVerMas.equals("no")){
            exitProcess(0)
        }else{
            println("Opción incorrecta")
            menuLeer()
        }
    }

//Funciones para tomar datos ingresados por tecladoe
    fun id_teclado(): String {
        return readLine()!!
    }

    fun nombre_personaje_teclado(): String {
        return readLine()!!
    }

    fun edad_teclado(): Int {
        return reader.nextInt()
    }

    fun genero_teclado(): String {
        return readLine()!!
    }

    fun nombre_pelicula_teclado(): String {
        return readLine()!!
    }

    fun es_princesa_teclado(): String {
        return readLine()!!
    }

    fun id_peliculas_teclado(): String {
        return readLine()!!
    }

    fun anio_creacion_teclado(): Int {
        return reader.nextInt()
    }

    fun numero_sagas_teclado(): Int {
        return reader.nextInt()
    }

    fun presupuesto_teclado(): Float {
        return reader.nextFloat()
    }

    fun exito_Mercado_teclado(): String {
        return readLine()!!
    }