package com.ncr.satemobile.scenarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.scenarios.adapter.ScenarioAdapter
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.scenario_fragment.*

class Scenario : Fragment() {

    companion object {
        fun newInstance() = Scenario()
    }

    private lateinit var viewModel: ScenarioViewModel
    private lateinit var scenarioAdapter: ScenarioAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scenario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val projectname = Prefs.getString("userProject", null)
         (activity as MainActivity?)!!.supportActionBar!!.subtitle = projectname
        scenariosrv.hasFixedSize()
        scenariosrv.layoutManager = LinearLayoutManager(activity)
        scenarioAdapter = ScenarioAdapter(activity = activity as MainActivity)
        viewModel = ViewModelProvider(this).get(ScenarioViewModel::class.java)
        viewModel.getScenarios(projectname).observe(activity as MainActivity, Observer {
            scenarioAdapter.setData(it)

        })
        scenariosrv.adapter = scenarioAdapter
    }

}