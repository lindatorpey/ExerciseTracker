package ie.wit.tritrack.ui.log

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.tritrack.R
import ie.wit.tritrack.databinding.FragmentLogBinding
import ie.wit.tritrack.main.TriTrackApp
import ie.wit.tritrack.models.LogModel

class LogFragment : Fragment() {

    lateinit var app: TriTrackApp
    var timeLogged = 0
    private var _fragBinding: FragmentLogBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val fragBinding get() = _fragBinding!!
    //lateinit var navController: NavController
    private lateinit var logViewModel: LogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as TriTrackApp
        setHasOptionsMenu(true)
        //navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _fragBinding = FragmentLogBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.log)

        logViewModel =
            ViewModelProvider(this).get(LogViewModel::class.java)
        //val textView: TextView = root.findViewById(R.id.text_home)
        logViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })

        //fragBinding.progressBar.max = 10000
        fragBinding.amountPicker.minValue = 1
        fragBinding.amountPicker.maxValue = 1000

        fragBinding.amountPicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            fragBinding.logTime.setText("$newVal")
        }
        setButtonListener(fragBinding)
        return root;
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LogFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun setButtonListener(layout: FragmentLogBinding) {
        layout.logbutton.setOnClickListener {
            val amount = if (layout.logTime.text.isNotEmpty())
                layout.logTime.text.toString().toInt() else layout.amountPicker.value
            if(timeLogged >= 0)
                Toast.makeText(context,"Time Logged", Toast.LENGTH_LONG).show()
            else {
                val logmethod = if(layout.logMethod.checkedRadioButtonId == R.id.logRun) "Run" else "Cycle"
                timeLogged += amount
                layout.totalTime.text = "$$timeLogged"
                //layout.progressBar.progress = totalDonated
                app.logsStore.create(LogModel(logmethod = logmethod,amount = amount))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_log, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
        timeLogged = app.logsStore.findAll().sumOf { it.amount }
        //fragBinding.progressBar.progress = totalDonated
        fragBinding.totalTime.text = "${timeLogged}"
    }
}
