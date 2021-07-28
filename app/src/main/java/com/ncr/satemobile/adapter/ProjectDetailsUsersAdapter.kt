package com.ncr.satemobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.bean.AssociatedProjectUser
import kotlinx.android.synthetic.main.projectdetails_users_row.view.*

class ProjectDetailsUsersAdapter(val activity: MainActivity) :
    RecyclerView.Adapter<ProjectDetailsUsersAdapter.ViewHolder>() {
    var userlist: List<AssociatedProjectUser> = emptyList<AssociatedProjectUser>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(user: AssociatedProjectUser) {
            itemView.pdtl_user_name.text = user.name
            itemView.pdtl_user_thumbnail.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_launcher));
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.projectdetails_users_row, parent, false)

        return ViewHolder(view)

    }
    fun setData(data: List<AssociatedProjectUser>) {
        data.let {
            this.userlist = it
            notifyDataSetChanged()
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userlist[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int {

        return userlist.size
    }
}