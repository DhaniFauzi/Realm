package com.example.realm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        tambah2()
    }

    fun getDataItem(){
        et_NAMA2.setText(intent.getStringExtra("nama"))
        et_catatan2.setText(intent.getStringExtra("email"))
    }

    fun setupRealm(){
        realm = Realm.getDefaultInstance()

    }

    fun delete2() {
        btn_DELETE2.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", intent.getIntExtra("id",1)).findFirst()
                .let {
                    it!!.deleteFromRealm()
                }
            realm.commitTransaction()
            startActivity(Intent(this, MainActivity::class.java))

        }
    }

    fun update2() {
        btn_UPDATE2.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", intent.getIntExtra("id",1)).findFirst()
                .let {
                    it!!.setNama(et_NAMA2.text.toString())
                    it!!.setCatatan(et_catatan2.text.toString())
                }
            realm.commitTransaction()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun tambah2(){
        btn_ADD2.setOnClickListener {
            realm.beginTransaction()
            val currentId = realm.where(User::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toInt()+1
            val User = realm.createObject(User::class.java)
            User.setId(nextId)
            User.setNama(et_NAMA2.text.toString())
            User.setCatatan(et_catatan2.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            realm.commitTransaction()
        }
    }
}