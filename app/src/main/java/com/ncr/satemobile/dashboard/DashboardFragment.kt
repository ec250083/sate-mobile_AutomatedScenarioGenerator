package com.ncr.satemobile.dashboard

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ncr.satemobile.MainActivity
import com.ncr.satemobile.R
import com.ncr.satemobile.databinding.DashboardFragmentBinding
import com.ncr.satemobile.log
import com.ncr.satemobile.utility.SpeedometerHelper
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAScrollablePlotArea
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.dashboard_fragment.speedviewlayout
import kotlinx.android.synthetic.main.speedlayoutrow.*
import kotlinx.android.synthetic.main.speedlayoutrowsecond.*

class DashboardFragment : Fragment() {
    var _binding: DashboardFragmentBinding? = null
    private lateinit var input_chartlabel: String
    private lateinit var input_chartdata: String
    private lateinit var input_chartName: String

    companion object {
        fun newInstance() = DashboardFragment()
    }

    val binding get() = _binding
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        loadCycleGraph()

    }


    fun loadCycleGraph(type: String? = null) {
        speedviewlayout.visibility = View.VISIBLE
        aa_chart_view.visibility = View.GONE
        var rate: Float? = null
        var rate_second: Float? = null
        viewModel.getCycleGraphData().observe(activity as MainActivity, Observer { chartresponse ->

            // receiveChartData(chartresponse, AAChartType.Pie)
            title.text=chartresponse[0].ChartName
            total_scenarios.text =
                getString(R.string.total_scenarios) + " " + chartresponse[0].TotalScenario
            passscenarios.text =
                getString(R.string.passscearios) + " " + chartresponse[0].PassScenario
            passrate.text =  getString(R.string.passrate)+" ${chartresponse[0].Passrate} %"
            rate = chartresponse[0].Passrate?.toFloat()

            speedometerDemo(rate!!,issecond = false)
            title_second.text=chartresponse[1].ChartName


            total_second.text = getString(R.string.total_scenarios) + " " + chartresponse[1].TotalScenario

            passscenarios_second.text =
                getString(R.string.passscearios) + " " + chartresponse[1].PassScenario

            passrate_second.text = getString(R.string.passrate) + " ${chartresponse[1].Passrate} %"
            rate_second = chartresponse[1].Passrate?.toFloat()
            speedometerDemo(rate_second!!, true)
        })
    }

    private fun speedometerDemo(value: Float, issecond: Boolean? = null) {
        activity?.runOnUiThread {
            if (issecond == true) {
                SpeedometerHelper.moveSpeedometer(
                    getDrawable(requireContext(), R.drawable.speedometer) as LayerDrawable,
                    speedViewsecond,
                    value,
                    "100".toFloat()
                )
            }
            SpeedometerHelper.moveSpeedometer(
                getDrawable(requireContext(), R.drawable.speedometer) as LayerDrawable,
                speedView,
                value,
                "100".toFloat()
            )
        }
    }

    fun callInitialGraph(type: String? = null) {
        speedviewlayout.visibility = View.GONE
        aa_chart_view.visibility = View.VISIBLE
        viewModel.getchartData().observe(activity as MainActivity, Observer {
            if (type == "Scenarios Executed in Current Year")
                receiveChartData(it[0], AAChartType.Column)
            else if (type == "Scenarios Created weekly progress") {
                receiveChartData(it[1], AAChartType.Line)
            }
        })
    }

    private fun receiveChartData(chartResponse: ChartResponse, graphType: Enum<AAChartType>) {
        //for (chart in chartResponse) {
        input_chartName = chartResponse.ChartName
        input_chartlabel = chartResponse.ChartLabels
        input_chartdata = chartResponse.ChartData
        // }
        loadchart(input_chartlabel, input_chartName, input_chartdata, graphType)
    }


    fun loadchart(label: String, name: String, data: String, graphType: Enum<AAChartType>) {
        activity?.log(data)
        val dataarray = arrayListOf<Int>()

        var result_label: Array<String> =
            label.substring(1, label.length - 1).split(",").toTypedArray()
        var result_data: Array<String> =
            data.substring(1, data.length - 1).split(",").toTypedArray()
        if (result_data.iterator().hasNext())
            for (a: String in result_data) {
                val elt = a.replace("\'", "")
                dataarray.add(Integer.valueOf(elt))
            }

        /*   if (graphType == AAChartType.Pie) {

               dataarrayPie[result_label.get(0)] = dataarray[0]
               dataarrayPie[result_label.get(1)] = dataarray[1]
               dataarrayPie[result_label.get(2)] = dataarray[2]
               dataarrayPie[result_label.get(3)] = dataarray[3]

           }*/


        val element1 = AASeriesElement()
            .name(name)
            .lineWidth(7f)
            .data(dataarray.toTypedArray())


        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(graphType as AAChartType)
            .scrollablePlotArea(AAScrollablePlotArea())
            .animationType(AAChartAnimationType.Bounce)
            .title(name)
            // .subtitle("subtitle")
            .categories(result_label)
            .backgroundColor(R.color.design_default_color_primary)
            .dataLabelsEnabled(true)
            .series(
                arrayOf(
                    element1
                )
            )

        binding?.aaChartView?.aa_drawChartWithChartModel(aaChartModel)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_settings_cycle) {
            log("Cycle")
            loadCycleGraph()
        }
        if (item.itemId == R.id.action_settings_weekly) {
            log("Weekly")
            callInitialGraph("Scenarios Created weekly progress")
        }
        if (item.itemId == R.id.action_settings_year) {
            log("year")
            callInitialGraph("Scenarios Executed in Current Year")
        }

        return super.onOptionsItemSelected(item)
    }

}