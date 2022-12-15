package com.mochire.tech.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mochire.tech.databinding.FragmentConditionsBinding
import com.mochire.tech.repository.ApiRepository
import com.mochire.tech.ui.adapters.ConditionsAdapter
import com.mochire.tech.viewmodels.ConditionsViewModel

class ConditionsFragment : Fragment() {

    private var _binding: FragmentConditionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val repository = ApiRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val conditionsViewModel =
            ViewModelProvider(this)[ConditionsViewModel::class.java]

        _binding = FragmentConditionsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        conditionsViewModel.getConditions()

        val adapter = ConditionsAdapter(conditionsViewModel.returnedConditions)
        binding.conditionsRecyclerView.adapter = adapter
        binding.conditionsRecyclerView.layoutManager = LinearLayoutManager(context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}