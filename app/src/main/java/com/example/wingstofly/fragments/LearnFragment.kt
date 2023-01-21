package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.QuestRecAdapter
import com.example.wingstofly.databinding.FragmentLearnBinding
import com.example.wingstofly.utils.Resource
import com.example.wingstofly.viewmodel.QuizViewModel

class LearnFragment : Fragment() {
    private lateinit var bind:FragmentLearnBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var topicsAdapter: QuestRecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentLearnBinding.inflate(layoutInflater)

        viewModel = (activity as MainActivity).questViewModel
        topicsAdapter = QuestRecAdapter()
        setUpRecView()

        viewModel.topics.observe(viewLifecycleOwner, Observer { topicCategories ->
            when(topicCategories){
                is Resource.Success -> {
                    topicCategories.newsData?.let {
                        topicsAdapter.listDiffer.submitList(it.trivia_categories)
                        Toast.makeText(context, "messages received", Toast.LENGTH_LONG).show()

                    }
                }

                is Resource.Failure ->{
                    topicCategories.questMessage?.let { message ->
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(context, "messages loading", Toast.LENGTH_LONG).show()
                }
            }

        })

//        viewModel.questions.observe(viewLifecycleOwner, Observer { questResources ->
//            when(questResources){
//                is Resource.Success ->{
//                    questResources.newsData?.let { topicData ->
//                        topicsAdapter.listDiffer.submitList(topicData.results)
//                        Toast.makeText(context, "messages received", Toast.LENGTH_LONG).show()
//
//                    }
//                }
//                is Resource.Failure ->{
//                    questResources.questMessage?.let { message ->
//                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                is Resource.Loading -> {
//                    Toast.makeText(context, "messages loading", Toast.LENGTH_LONG).show()
//                }
//
//            }
//
//        })


        return bind.root
    }

    private fun setUpRecView(){

        bind.recView1.apply {
            adapter = topicsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}