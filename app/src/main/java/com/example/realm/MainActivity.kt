package com.example.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realm.adapter.User_Adapter
import com.example.realm.model.User
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userAdapter: User_Adapter
    lateinit var realm : Realm
    val lm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambah()
        initview()
        delete()
        update()
    }


    private fun getAlluser(){
        realm.where(User::class.java).findAll().let {
            userAdapter.setUser(it)
        }
    }


    private fun initview(){
        rv_item.layoutManager = lm
        userAdapter = User_Adapter(this)
        rv_item.adapter = userAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAlluser()
    }


    fun tambah(){
        btn_ADD.setOnClickListener{
            realm.beginTransaction()
            var count = 0
            realm.where(User::class.java).findAll().let {
                for (i in it){
                    count++
                }
            }
            try {
                var user = realm.createObject(User::class.java)
                user.setId(count+1)
                user.setNama(et_NAMA.text.toString())
                user.setEmail(et_EMAIL.text.toString())
                et_ID.setText("")
                et_NAMA.setText("")
                et_EMAIL.setText("")
                realm.commitTransaction()
                getAlluser()
            }catch (e:RealmException){

            }
        }
    }

    fun delete(){
        btn_DELETE.setOnClickListener{
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", et_ID.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAlluser()
        }
    }

    fun update(){
        btn_UPDATE.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", et_ID.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_NAMA.text.toString())
                it!!.setEmail(et_EMAIL.text.toString())
            }
            realm.commitTransaction()
            getAlluser()
        }
    }

}