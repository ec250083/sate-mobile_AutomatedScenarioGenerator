package com.ncr.satemobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.bean.ProjectBean
import kotlinx.android.synthetic.main.project_row.view.*

class ProjectAdapter(val activity: MainActivity) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {
    private var projectBeanList: List<ProjectBean> = emptyList<ProjectBean>()

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data: ProjectBean) {
            itemView.pd_user_name.text = data.projectName
            itemView.pd_user_thumbnail.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_launcher));
        }

    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val projectBeanData = projectBeanList[position]
        holder.bindData(projectBeanData)
       /* holder.itemView.setOnClickListener {
            holder.itemView.context.showToast(holder.itemView.projectname.text.toString())
        }*/

    }
    fun setData(data: List<ProjectBean>) {
        data.let {
            this.projectBeanList = it
            notifyDataSetChanged()
        }
    }
    override fun getItemCount(): Int {
        return projectBeanList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.project_row, parent, false)
        return ProjectViewHolder(itemview)

    }

}