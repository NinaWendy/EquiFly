package com.example.wingstofly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.databinding.ScholarSuggestionBinding
import com.example.wingstofly.models.Scholar
import kotlinx.android.synthetic.main.fragment_chat.view.top_rec_view
import kotlinx.android.synthetic.main.fragment_scholar.view.*
import kotlinx.android.synthetic.main.scholar.view.*

class ScholarsRecAdapter(var context: Context): RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind: ViewBinding): RecyclerView.ViewHolder(bind.root) {
        val v = ScholarBinding.inflate(LayoutInflater.from(context))
        val v1 = ScholarSuggestionBinding.inflate(LayoutInflater.from(context))

        fun setData(scholar:Scholar){
          if (v.root.id == bind.root.id){
              (bind as ScholarBinding).scholarStatus.text = scholar.secondarySchool
              bind.scholarName.text = scholar.getName()
              bind.status.text = scholar.getStatus()
          }
          if(v1.root.id == bind.root.id){
              val newName = scholar.getName().trim().substring(scholar.getName().indexOf(" ") )
              (bind as ScholarSuggestionBinding).scholarName.text = "${scholar.getName().substring(0, 1)}.$newName"
          }

        }
    }

    private val diffUtil = object: DiffUtil.ItemCallback<Scholar>(){
        override fun areItemsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem.getId() == newItem.getId()
        }

        override fun areContentsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem.equals(newItem)
        }

    }

    val listDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view: ViewBinding? = null
        if (parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.myRecView.id){
            view = ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentChatBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            view = ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val scholar = listDiffer.currentList[position]
        holder.setData(scholar)



    }

    override fun getItemCount() = listDiffer.currentList.size
}