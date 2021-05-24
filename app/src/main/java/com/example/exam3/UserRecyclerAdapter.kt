package com.example.exam3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam3.databinding.UserItemBinding

class UserRecyclerAdapter(
    private val items: MutableList<User>,
    val delete: (Int) -> Unit,
    val update: (Int) -> Unit,
) : RecyclerView.Adapter<UserRecyclerAdapter.ItemViewHolder>() {

    private lateinit var binding:UserItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvName.text = items[adapterPosition].name
            binding.tvSecondName.text =
                items[adapterPosition].secondName
            binding.tvEmail.text = items[adapterPosition].email
            binding.btnDelete.setOnClickListener {
                delete(adapterPosition)
            }
            binding.btnUpdate.setOnClickListener {
                update(adapterPosition)
            }
        }
    }
}