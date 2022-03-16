package ie.wit.exercisetracker.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class ExerciseModel(var id: Long = 0,
                         val logmethod: String = "N/A",
                         val amount: Int = 0) : Parcelable