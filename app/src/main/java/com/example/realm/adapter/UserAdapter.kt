package com.example.realm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realm.R
import com.example.realm.model.User

class UserAdapter(private val users: MutableList<User>,private val listener: UserAdapter.onAdapterListener ) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }
    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bindModel(users[position])
        holder.itemView.setOnClickListener{
            listener.onClick(users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
    fun setUser(data:List<User>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(i : View):RecyclerView.ViewHolder(i){
        val tvId : TextView = i.findViewById(R.id.tv_id)
        val tvNama : TextView = i.findViewById(R.id.tv_nama)
        val tvCatatan : TextView = i.findViewById(R.id.tv_catatan)

        fun bindModel (u:User){
            tvId.text = u.getId().toString()
            tvNama.text = u.getNama()
            tvCatatan.text = u.getCatatan()
        }

    }

    interface onAdapterListener{
        fun onClick(User: User)
    }
}