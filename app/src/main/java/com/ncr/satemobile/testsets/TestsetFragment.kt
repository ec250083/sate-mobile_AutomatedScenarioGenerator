package com.ncr.satemobile.testsets

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
import com.ncr.satemobile.testsets.adapter.TestsetAdapter
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.testset_fragment.*

class TestsetFragment : Fragment() {

    companion object {
        fun newInstance() = TestsetFragment()
    }

    private lateinit var viewModel: TestsetViewModel
    private lateinit var testsetAdapter: TestsetAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.testset_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val projectname = Prefs.getString("userProject", null)
        (activity as MainActivity?)!!.supportActionBar!!.subtitle = projectname
        testsetrv.hasFixedSize()
        testsetrv.layoutManager = LinearLayoutManager(requireActivity())
        testsetAdapter = TestsetAdapter(requireActivity() as MainActivity)
        viewModel = ViewModelProvider(this).get(TestsetViewModel::class.java)
        viewModel.getTestsets(Prefs.getString("userProject", null)).observe(requireActivity(),
            Observer {
                testsetAdapter.setData(it)
            })
        testsetrv.adapter = testsetAdapter

    }

}