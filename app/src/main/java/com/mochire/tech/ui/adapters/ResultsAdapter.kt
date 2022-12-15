package com.mochire.tech.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mochire.tech.R
import com.mochire.tech.models.Condition

class ResultsAdapter(private val conditions: List<Condition>): RecyclerView.Adapter<ResultsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.assessment_result_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.conditionName.text = conditions[position].common_name
        holder.conditionProbability.text ="Probability: " + conditions[position].probability.toString()
    }

    override fun getItemCount(): Int {
        return conditions.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val conditionName: TextView = itemView.findViewById(R.id.conditionName)
        val conditionProbability: TextView = itemView.findViewById(R.id.conditionProbability)

    }


}