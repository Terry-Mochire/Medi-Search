package com.mochire.tech.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mochire.tech.R
import com.mochire.tech.models.Conditions

class ConditionsAdapter(private val conditions: List<Conditions>) : RecyclerView.Adapter<ConditionsAdapter.ViewHolder>() {


   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val conditionName: TextView = itemView.findViewById(R.id.conditionName)
       val conditionCommonName: TextView = itemView.findViewById(R.id.conditionProbability)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.conditions_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.conditionCommonName.text = conditions[position].common_name
        holder.conditionName.text = conditions[position].name
        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle(conditions[position].common_name)
            builder.setMessage(conditions[position].extras.hint)
            builder.setCancelable(true)
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return conditions.size
    }


}