package com.ncr.satemobile.scenarios.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.scenarios.bean.ScenarioBean
import kotlinx.android.synthetic.main.scenario_row.view.*

class ScenarioAdapter(activity: MainActivity) : RecyclerView.Adapter<ScenarioAdapter.ViewHolder>() {

    var scenarios: List<ScenarioBean> = emptyList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(scenarioBean: ScenarioBean) {
            itemView.sce_name.text =scenarioBean.scenarioName
            itemView.sce_desc.text = scenarioBean.scenarioDesc
            itemView.sce_type.text = scenarioBean.scenarioType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scenario_row, parent, false)
        )
    }

    fun setData(scenarioList: List<ScenarioBean>): List<ScenarioBean> {
        this.scenarios = scenarioList;
        notifyDataSetChanged()
        return scenarios
    }

    override fun getItemCount(): Int {
        return scenarios.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scenarioBean = scenarios[position]
        holder.bindView(scenarioBean)
    }
}