package ie.wit.exercisetracker.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.exercisetracker.R
import ie.wit.exercisetracker.databinding.FragmentExerciseBinding
import ie.wit.exercisetracker.main.ExerciseXApp
import ie.wit.exercisetracker.models.ExerciseModel

class ExerciseFragment : Fragment() {

    lateinit var app: ExerciseXApp
    var totalExercised = 0
    private var _fragBinding: FragmentExerciseBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ExerciseXApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentExerciseBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_exercise)

        fragBinding.progressBar.max = 10000
        fragBinding.distancePicker.minValue = 1
        fragBinding.distancePicker.maxValue = 30

        fragBinding.distancePicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            fragBinding.logAmount.setText("$newVal")
        }
        return root;
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
    override fun onResume() {
        super.onResume()
        totalExercised = app.exerciseStore.findAll().sumOf { it.amount }
        fragBinding.progressBar.progress = totalExercised
        fragBinding.totalSoFar.text = "$$totalExercised"
    }
    fun setButtonListener(layout: FragmentExerciseBinding) {
        layout.exerciseButton.setOnClickListener {
            val amount = if (layout.logAmount.text.isNotEmpty())
                layout.logAmount.text.toString().toInt() else layout.distancePicker.value
            if(totalExercised >= layout.progressBar.max)
                Toast.makeText(context,"Excercise Amount Exceeded!",Toast.LENGTH_LONG).show()
            else {
                val logmethod = if(layout.logMethod.checkedRadioButtonId == R.id.logWalk) "Walk" else "Run"
                totalExercised += amount
                layout.totalSoFar.text = "$$totalExercised"
                layout.progressBar.progress = totalExercised
                app.exerciseStore.create(ExerciseModel(logmethod = logmethod ,amount = amount))
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_exercise, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExerciseFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}