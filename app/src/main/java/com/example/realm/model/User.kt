package com.example.realm.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class User : RealmObject(){
    private var id : Int? = null
    private var nama : String? = null
    private var catatan : String ? = null

    fun setId(id:Int){
        this.id = id
    }

    fun getId():Int?{
        return id
    }

    fun setNama(nama:String){
        this.nama = nama
    }

    fun getNama():String?{
        return nama
    }

    fun setCatatan(catatan:String){
        this.catatan = catatan
    }

    fun getCatatan():String?{
        return catatan
    }
}