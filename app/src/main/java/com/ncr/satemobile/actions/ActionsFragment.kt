package com.ncr.satemobile.actions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.scenarios.adapter.ActionsAdapter
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.actions_fragment.*

class ActionsFragment : Fragment() {

    companion object {
        fun newInstance() = ActionsFragment()
    }

    private lateinit var viewModel: ActionsViewModel
    private lateinit var actionsAdapter: ActionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val projectname = Prefs.getString("userProject", null)
        (activity as MainActivity?)!!.supportActionBar!!.subtitle = projectname
        actionsrv.hasFixedSize()
        actionsrv.layoutManager=LinearLayoutManager(requireActivity())
        viewModel = ViewModelProvider(this).get(ActionsViewModel::class.java)
        actionsAdapter= ActionsAdapter(requireActivity() as MainActivity)
        viewModel.getActions(Prefs.getString("userProject", null)).observe(requireActivity(), Observer {
            actionsAdapter.setData(it)

        })
 actionsrv.adapter=actionsAdapter
     }

}