package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.ActivityJobDetailsRecViewBinding

class JobDetailsRecView(val list: ArrayList<String>): RecyclerView.Adapter<JobDetailsRecView.MyHolder>() {
    inner class MyHolder(private val bind:ActivityJobDetailsRecViewBinding): RecyclerView.ViewHolder(bind.root){
            fun setData(item: String){
                bind.description.text = "${list.indexOf(item) + 1} - $item"
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ActivityJobDetailsRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(list[position])

    }

    override fun getItemCount() = list.size
}