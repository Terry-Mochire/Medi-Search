package com.mochire.tech.ui.main.home

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mochire.tech.databinding.FragmentAssessmentBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AssessmentFragment: Fragment() {
    private var _binding: FragmentAssessmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentAssessmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchView: SearchView = binding.searchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setIconifiedByDefault(false)

        val listView: ListView = binding.listView



        GlobalScope.launch {
            val symptoms= homeViewModel.loadSymptoms()
            val symptomNames = symptoms.keys.toList()
            val symptomId = symptoms.values.toList()
            Log.d("symptoms", symptomId[3])
            Log.i("symptoms", symptomNames[3])

//            listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, symptomNames)



            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("query", query.toString())
                    val submitSymptomId = symptoms[query]
                    Log.d("submitSymptomId", submitSymptomId.toString())
                    return false
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    val filteredList = symptomNames.filter { it.contains(newText.toString(), true) }
                    listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filteredList)
                    (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    return false
                }
            })

            listView.setOnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position) as String
                searchView.setQuery(item, false)
            }



        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


