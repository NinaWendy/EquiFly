package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.ActivityJobRecviewBinding
import com.example.wingstofly.models.Job

class JobsRecView: RecyclerView.Adapter<JobsRecView.MyHolder>() {
    inner class MyHolder(val bind: ActivityJobRecviewBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(job: Job){
            bind.newsId.text = job.title
            bind.newsDescription.text = "${job.description.subSequence(0, 150)}....."
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Job>(){
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return  oldItem.jobUrl == newItem.jobUrl
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return  oldItem == newItem
        }

    }

    val asyncList = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ActivityJobRecviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(asyncList.currentList[position])
    }

    override fun getItemCount() = asyncList.currentList.size
}