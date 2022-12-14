package com.mochire.tech.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.mochire.tech.R

class QuestionsAdapter(private val options: List<String>) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.assessment_questions_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.radioButton.text = options[position]
        holder.radioButton.setOnClickListener {
            holder.radioButton.isChecked = true
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

    }

}