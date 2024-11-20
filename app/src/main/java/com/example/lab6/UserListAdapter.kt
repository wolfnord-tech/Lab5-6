package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.Model.User

class UserListAdapter(private var users: List<User>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.itemTextView)
        val textView2: TextView = view.findViewById(R.id.textView)
        val textView3: TextView = view.findViewById(R.id.textView2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.textView.text = user.id.toString()
        holder.textView2.text = user.firstName
        holder.textView3.text = user.lastName

    }

    fun updateData(newItems: List<User>) {
        users = newItems
        notifyDataSetChanged()
    }

}