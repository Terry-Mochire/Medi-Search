package com.mochire.tech.ui.main.fragments

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mochire.tech.R
import com.mochire.tech.databinding.FragmentAssessmentBinding
import com.mochire.tech.models.Age
import com.mochire.tech.models.Evidence
import com.mochire.tech.models.Patient
import com.mochire.tech.ui.adapters.QuestionsAdapter
import com.mochire.tech.ui.adapters.ResultsAdapter
import com.mochire.tech.viewmodels.HomeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AssessmentFragment : Fragment() {
    private var _binding: FragmentAssessmentBinding? = null
    private val binding get() = _binding!!

    @OptIn(DelicateCoroutinesApi::class)
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

        val evidence = mutableListOf<Evidence>()
        val patient = Patient(Age(30), evidence, "male")

        val listView: ListView = binding.listView
        val recyclerView: RecyclerView = binding.recyclerView
        val assessmentQuestion: TextView = binding.assessmentQuestion
        val getDiagnosisButton: Button = binding.getDiagnosisButton
        homeViewModel.submitSymptomId = ""

        GlobalScope.launch {
            val symptoms = homeViewModel.symptomNameWithId.await()
            val symptomNames = symptoms.keys.toList()


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val submittedSymptomId = symptoms[query]
                    Log.d("submitSymptomId", submittedSymptomId.toString())
                    evidence.add(Evidence("present", submittedSymptomId.toString()))
                    homeViewModel.getDiagnosis(submittedSymptomId.toString())
                    homeViewModel.submitSymptomId = submittedSymptomId.toString()
                    listView.visibility = View.GONE
                    assessmentQuestion.visibility = View.VISIBLE
                    assessmentQuestion.text = homeViewModel.question
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = QuestionsAdapter(homeViewModel.data)
                    getDiagnosisButton.visibility = View.VISIBLE
                    return false
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    val filteredList = symptomNames.filter { it.contains(newText.toString(), true) }
                    listView.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        filteredList
                    )
                    (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
                    return false
                }
            })

            listView.setOnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position) as String
                searchView.setQuery(item, false)
            }

            getDiagnosisButton.setOnClickListener {
               val int = recyclerView.adapter?.itemCount
                for (i in 0 until int!!) {
                    val radioButton: RadioButton = recyclerView.findViewHolderForAdapterPosition(i)?.itemView?.findViewById(R.id.radioButton)!!
                    if (radioButton.isChecked) {
                        val selectedChoiceId = homeViewModel.getAssessmentQuestionId(radioButton.text.toString())
                        evidence.add(Evidence("present", selectedChoiceId))
                        homeViewModel.getDiagnosis(selectedChoiceId)
                        homeViewModel.getSpecialist(patient)
                    }
                }
                assessmentQuestion.text = "Specialist: " + homeViewModel.recommendedSpecialist
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ResultsAdapter(homeViewModel.returnedConditions)

                getDiagnosisButton.visibility = View.GONE
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


