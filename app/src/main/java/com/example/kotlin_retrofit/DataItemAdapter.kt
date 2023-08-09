package com.example.kotlin_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_retrofit.databinding.DataItemsBinding

class DataItemAdapter(val context: Context, val userList: List<MyDataItem>): RecyclerView.Adapter<DataItemAdapter.ViewHolder>() {

    private lateinit var binding: DataItemsBinding
    class ViewHolder(private val binding: DataItemsBinding): RecyclerView.ViewHolder(binding.root){
        var userID: TextView
        var userTitle: TextView

        init {
            userID = binding.userID
            userTitle = binding.userTitle
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userID.text = userList[position].userId.toString()
        holder.userTitle.text = userList[position].title.toString()

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}