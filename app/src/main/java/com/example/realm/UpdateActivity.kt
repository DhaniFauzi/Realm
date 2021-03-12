package com.example.realm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.realm.model.User
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_update.*



class UpdateActivity : AppCompatActivity() {
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getDataItem()
        setupRealm()
        delete2()
        update2()
    }

    fun getDataItem() {
        et_NAMA2.setText(intent.getStringExtra("nama"))
        et_catatan2.setText(intent.getStringExtra("catatan"))
    }

    fun setupRealm() {
        realm = Realm.getDefaultInstance()

    }

    fun delete2() {
        btn_DELETE2.setOnClickListener {
            deleteDialog()
        }
    }

    fun update2() {
        btn_UPDATE2.setOnClickListener {
            if (et_NAMA2.text.toString().isEmpty()) {
                Toast.makeText(this, "Judul harus diisi", Toast.LENGTH_SHORT).show()
            } else if (et_catatan2.text.toString().isEmpty()) {
                Toast.makeText(this, "Catatan harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                realm.beginTransaction()
                realm.where(User::class.java).equalTo("id", intent.getIntExtra("id", 1)).findFirst()
                    .let {
                        it!!.setNama(et_NAMA2.text.toString())
                        it!!.setCatatan(et_catatan2.text.toString())
                    }
                realm.commitTransaction()
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "Anda telah mengubah catatan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin anda ingin menghapus catatan ini ?")
            setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }

            setPositiveButton("Hapus") { dialog, which ->
                dialog.dismiss()
                realm.beginTransaction()
                realm.where(User::class.java).equalTo("id", intent.getIntExtra("id", 1)).findFirst()
                    .let {
                        it!!.deleteFromRealm()
                    }
                realm.commitTransaction()
                intentMainActivity()
            }
        }
        alertDialog.show()
    }

    fun intentMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}



