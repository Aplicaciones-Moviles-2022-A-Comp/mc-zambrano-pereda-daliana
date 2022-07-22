package com.example.movcompdzp2022a

import android.os.Parcel
import android.os.Parcelable

class Pelicula (
    val idPelicula: Int,
    var nombrePelicula: String?,
    var anioCreacion: Int?,
    var numSagas: String?, //puede que sea string por el spinner
    var presupuesto: Int?,
    var tuvoExito: String?

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(String::class.java.classLoader) as? String,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPelicula)
        parcel.writeString(nombrePelicula)
        parcel.writeValue(anioCreacion)
        parcel.writeValue(numSagas)
        parcel.writeValue(presupuesto)
        parcel.writeString(tuvoExito)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
}