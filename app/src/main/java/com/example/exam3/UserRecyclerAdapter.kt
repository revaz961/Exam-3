package com.example.exam3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRecyclerAdapter(
    private val items: MutableList<User>,
    val delete: (Int) -> Unit,
    val update: (Int) -> Unit,
) : RecyclerView.Adapter<UserRecyclerAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.findViewById<TextView>(R.id.tvName).text = items[adapterPosition].name
            itemView.findViewById<TextView>(R.id.tvSecondName).text =
                items[adapterPosition].secondName
            itemView.findViewById<TextView>(R.id.tvEmail).text = items[adapterPosition].email
            itemView.findViewById<ImageView>(R.id.btnDelete).setOnClickListener {
                delete(adapterPosition)
            }
            itemView.findViewById<ImageView>(R.id.btnUpdate).setOnClickListener {
                update(adapterPosition)
            }
        }
    }
}