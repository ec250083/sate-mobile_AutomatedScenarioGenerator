package com.ncr.satemobile.testsets.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.testsets.bean.Testsetbean
import kotlinx.android.synthetic.main.scenario_row.view.*

class TestsetAdapter(activity: MainActivity) : RecyclerView.Adapter<TestsetAdapter.ViewHolder>() {

    var testsets: List<Testsetbean> = emptyList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(Testsetbean: Testsetbean) {
            itemView.sce_name.text = Testsetbean.tsName
            itemView.sce_type.text = Testsetbean.tsDesc
            itemView.sce_desc.text = Testsetbean.tsCreateBy
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scenario_row, parent, false)
        )
    }

    fun setData(testsetsList: List<Testsetbean>): List<Testsetbean> {
        this.testsets = testsetsList;
        notifyDataSetChanged()
        return testsets
    }

    override fun getItemCount(): Int {
        return testsets.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Testsetbean = testsets[position]
        holder.bindView(Testsetbean)
    }
}