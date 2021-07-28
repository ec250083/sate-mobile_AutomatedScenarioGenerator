package com.ncr.satemobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncr.satemobile.adapter.ProjectDetailsUsersAdapter
import com.ncr.satemobile.databinding.FragmentSecondBinding
import com.ncr.satemobile.veiwmodel.ProjectDetailsWithUsersViewModel
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.project_row.*
import kotlinx.android.synthetic.main.projectdetails_users_row.*
import kotlinx.android.synthetic.main.projectusers.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var projectDetailsViewModel: ProjectDetailsWithUsersViewModel
    var projectDetailsUsersAdapter: ProjectDetailsUsersAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val projectName = Prefs.getString("userProject", null)
        (activity as MainActivity?)!!.supportActionBar!!.subtitle = projectName
        projectdetails_users.hasFixedSize()
        projectdetails_users.layoutManager = LinearLayoutManager(activity)
        projectdetails_users.itemAnimator = DefaultItemAnimator()
        projectDetailsUsersAdapter = ProjectDetailsUsersAdapter(activity = activity as MainActivity)
        projectDetailsViewModel = ProjectDetailsWithUsersViewModel()
        projectName?.let { pd ->
            projectDetailsViewModel.getProjectDetails(pd)
                ?.observe(activity as MainActivity, Observer {
                    txt_parentsetname.text = it.parentSetName
                    txt_pospath.text = it.posPath
                    txt_pospathtarget.text = it.posPathTarget
                    txt_projectpath.text = it.projectPath
                    txt_smtpserver.text = it.smtpserver

                })
        }
        projectName?.let {
            projectDetailsViewModel.getProjectUsers(it)?.observe(activity as MainActivity,
                Observer {
                    projectDetailsUsersAdapter?.setData(it)
                })
            projectdetails_users.adapter = projectDetailsUsersAdapter
        }
        /* binding.buttonSecond.setOnClickListener {
             findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
         }*/


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}