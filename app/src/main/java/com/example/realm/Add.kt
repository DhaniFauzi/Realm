package com.example.realm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realm.model.User
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update.*

class Add : AppCompatActivity() {
    lateinit var realm : Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        tambah2()
        setupRealm()
    }

    fun tambah2() {
        btn_ADD2.setOnClickListener {
            if (et_NAMA3.text.toString().isEmpty()) {
                Toast.makeText(this, "Judul harus diisi", Toast.LENGTH_SHORT).show()
            } else if (et_catatan3.text.toString().isEmpty()) {
                Toast.makeText(this, "Catatan harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                realm.beginTransaction()
                val currentId = realm.where(User::class.java).max("id")
                val nextId = if (currentId == null) 1 else currentId.toInt() + 1
                val User = realm.createObject(User::class.java)
                User.setId(nextId)
                User.setNama(et_NAMA3.text.toString())
                User.setCatatan(et_catatan3.text.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                realm.commitTransaction()
                Toast.makeText(this, "Anda telah menambahkan catatan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setupRealm() {
        realm = Realm.getDefaultInstance()

    }
}