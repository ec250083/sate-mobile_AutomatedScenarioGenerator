package com.ncr.satemobile.scenarios.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.actions.Actions
import kotlinx.android.synthetic.main.scenario_row.view.*

class ActionsAdapter(activity: MainActivity) : RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    var actions: List<Actions> = emptyList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(action: Actions) {
            itemView.sce_name.text = action.unitName
             itemView.sce_desc.visibility=View.INVISIBLE
            itemView.sce_type.text = action.unitDesc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scenario_row, parent, false)
        )
    }
fun setData(actionsList: List<Actions>): List<Actions> {
    this.actions=actionsList;
    notifyDataSetChanged()
    return actions
}
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actionsBean = actions[position]
        holder.bindView(actionsBean)
    }

    override fun getItemCount(): Int {
        return actions.size
    }
}