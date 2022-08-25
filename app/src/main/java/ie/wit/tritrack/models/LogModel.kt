package ie.wit.tritrack.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LogModel(var id: Long = 0,
                         val logmethod: String = "N/A",
                         val amount: Int = 0) : Parcelable