package com.ncr.satemobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncr.satemobile.adapter.ProjectAdapter
import com.ncr.satemobile.bean.ProjectBean
import com.ncr.satemobile.databinding.FragmentFirstBinding
import com.ncr.satemobile.utility.OnItemClickListener
import com.ncr.satemobile.utility.addOnItemClickListener
import com.ncr.satemobile.veiwmodel.ProjectsViewModel
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.project_row.view.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var dataList: ArrayList<ProjectBean>? = null
    var mprojectAdapter: ProjectAdapter? = null
    var projectViewModel: ProjectsViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList = arrayListOf()
        dataList?.clear()
        mprojectAdapter = ProjectAdapter(activity = activity as MainActivity)
     /*   val projectname = Prefs.getString("userProject", null)
        (activity as MainActivity?)!!.supportActionBar!!.subtitle = projectname*/
        projects_list.hasFixedSize()
        projects_list.layoutManager = LinearLayoutManager(activity)
        projects_list.itemAnimator = DefaultItemAnimator()
        projectViewModel = ProjectsViewModel()
        if (Prefs.getString("userName", null)!=null)
        {

         projectViewModel?.getProjectsByUser(Prefs.getString("userName", null))?.observe(activity as MainActivity, Observer {
             dataList = it as ArrayList<ProjectBean>?
            mprojectAdapter?.setData(dataList!!)
        })
        }
        projects_list.adapter = mprojectAdapter
        projects_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val bundle = bundleOf("project" to view.pd_user_name.text.toString())
                 findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}