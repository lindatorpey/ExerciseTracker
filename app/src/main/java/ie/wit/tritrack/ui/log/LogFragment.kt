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
            //Create variable amount and check if text view with log time is not empty
            val amount = if (layout.logTime.text.isNotEmpty())
                //convert the logtime entered to a string and then to an int, otherwise use 0 as default
                layout.logTime.text.toString().toInt() else 0
                //Toast.makeText(context,"Please Enter your Time", Toast.LENGTH_LONG).show()
            //Check if timelogged(variable created at top of file) is greater than or equal to zero
            if(amount >= 0)
                //Pop up that time has been logged
                Toast.makeText(context,"Time Logged", Toast.LENGTH_LONG).show()
            else {
                //create varaible logmethod, check if the radio button is checked on Run, "Run"
                val logmethod = if(layout.logMethod.checkedRadioButtonId == R.id.logRun)
                    "Run"
                else if (
                    //if the radio button is checked on swim"Swim"
                    layout.logMethod.checkedRadioButtonId == R.id.logSwim
                ) { "Swim" }
                //Otherwise cycle
                else "Cycle"
                //Add the amount entered in the text view ot the timelogged variable
                timeLogged += amount
                //Change the totalTime text view to the value of the timelogged variable
                layout.totalTime.text = "$timeLogged"
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
