package ie.wit.exercisetracker.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.exercisetracker.R
import ie.wit.exercisetracker.databinding.CardExerciseBinding
import ie.wit.exercisetracker.models.ExerciseModel

class ExerciseAdapter constructor(private var exercises: List<ExerciseModel>)
    : RecyclerView.Adapter<ExerciseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardExerciseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val exercise = exercises[holder.adapterPosition]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int = exercises.size

    inner class MainHolder(val binding : CardExerciseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: ExerciseModel) {
            binding.logAmount.text = exercise.amount.toString()
            binding.logMethod.text = exercise.logmethod
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}

