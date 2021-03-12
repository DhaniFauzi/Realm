package com.example.realm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realm.adapter.UserAdapter
import com.example.realm.model.User
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var UserAdapter: UserAdapter
    lateinit var realm: Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonadd()
        initview()
        getAlluser()
    }

    fun buttonadd(){
        btn_tambah.setOnClickListener {
            startActivity(Intent(this, Add::class.java))
            finish()
        }
    }
    private fun getAlluser() {
        realm.where(User::class.java).findAll().let {
            UserAdapter.setUser(it)
        }
    }

    fun initview(){
    rv_item.layoutManager = lm
    UserAdapter = UserAdapter(mutableListOf(),object : UserAdapter.onAdapterListener{
        override fun onClick(User:User) {
            startActivity(Intent(this@MainActivity,UpdateActivity::class.java)
                .putExtra("id", User.getId())
                .putExtra("nama", User.getNama())
                .putExtra("catatan", User.getCatatan())
            )
        }
    })
    rv_item.adapter = UserAdapter
    Realm.init(applicationContext)
    realm = Realm.getDefaultInstance()
    getAlluser()
}
}
