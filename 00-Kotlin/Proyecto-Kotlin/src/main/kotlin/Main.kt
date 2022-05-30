import java.util.Date

//Main.kt
fun main () {
    println("Hola mundo")

    //Tipos de variables
    //INMUTABLES - No se puede reasignar (=)
    val inmutable: String = "Daliana";
    //inmutable = "hola" ---- no se puede hacer

    //MUTABLES - Se pueden reasignar (!=)
    var mutable: String = "Zambrano"
    mutable = "Pereda"

    // val > var se desea trbajar siempre con mutables

    //Sintaxis Duck typing
    val ejemploVariable = "Ejemplo" //string
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'D'
    val mayorEdad: Boolean = true

    //Clases JAVA
    val fechaNacimiento: Date = Date()

    //Condicionales
    if (true) {

    } else {

    }

    //Switch no existe, en su defecto existe el when
    val estadoCivilWhen = "S"

    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }
        "UN" -> println("hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    imprimirNombre("Daliana")
    Suma.pi
    val sumaUno = Suma(1,2)
    val sumaDos = Suma(3,4)

    sumaUno.sumar()
    sumaDos.sumar()
    println(Suma.historialSumas)

    //ARREGLOS - Tipos

    // Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    // Arreglo Dinámicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // OPERADORES -> Sirven para los arreglos estáticos y dinámicos


    // FOR EACH -> Unit
    // Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach {
                valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    // MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
//        .map { valorActual: Int ->
//            return@map valorActual + 15
//        }

    println(respuestaMapDos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any {valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all {valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false
}

//función para imprimir
//Unit == void
fun imprimirNombre (nombre: String): Unit {
    println("Nombre : ${nombre}")
}

//FUNCION CALCULAR SUELDO
fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (Defecto)
    bonoEspecial: Double? = null, //Opcional (null) -> nullable
): Double{
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    }else {
        return sueldo *  (100 / tasa) + bonoEspecial
    }
}

//CLASE ABSTRACTA
abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){ //Bloque de código del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("inicializado")
    }
}

abstract class Numeros (
    //uno: Int, //Parametro
    //public var uno: Int, //Propiedad de la clase pública
    //var uno: Int, //Propiedad de la clase pública
    protected val numeroUno: Int,
    protected val numeroDos: Int
){
    init { //Bloque código constructor PRIMARIO
        //this.numeroUno //Propiedad de la clase, this es OPCIONAL
        //this.numeroDos //Propiedad de la clase, this es OPCIONAL
        numeroDos //Propiedad de la clase
        numeroUno //Propiedad de la clase
        println("inicializado")
    }
}

class Suma ( //Constructor primario de Suma
    uno: Int,
    dos: Int
): Numeros ( //Super constructor de Numeros //
    uno,
    dos,
){
    init { //Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor( //Segundo constructor
        uno: Int?, //el ? significa que el numero puede ser nulo
        dos: Int //parametros
    ): this ( //llamada al constructor primario
        if (uno == null) 0 else uno,
        dos
    ){
        //bloque código segundo constructor
    }

    constructor( //Tercer constructor
        uno: Int,
        dos: Int?
    ): this ( //llamada al constructor primario
        uno,
        if (dos == null) 0 else dos,
    ){
        //bloque código tercer constructor
    }

    constructor( //Cuarto constructor
        uno: Int?,
        dos: Int?
    ): this ( //llamada al constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    ){
        //bloque código tercer constructor
    }

    //método
    fun sumar (): Int {
        val total = numeroUno + numeroDos
        return total
    }

    //Aquí no existe el static, sino el companion object
    companion object {
        val pi = 3.14
        fun elevarAlCuadrado(num:Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}