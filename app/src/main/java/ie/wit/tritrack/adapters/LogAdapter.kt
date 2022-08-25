package ie.wit.tritrack.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.tritrack.databinding.LogCardBinding
import ie.wit.tritrack.models.LogModel

class LogAdapter constructor(private var logs: List<LogModel>) : RecyclerView.Adapter<LogAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):MainHolder{
        val binding = LogCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }
    override fun  onBindViewHolder(holder: MainHolder, position: Int){
        val log = logs[holder.adapterPosition]
        holder.bind(log)
    }
    override fun getItemCount(): Int = logs.size

    inner class MainHolder(val binding : LogCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(log: LogModel){
            binding.logamount.text = log.amount.toString()
            binding.logtype.text = log.logmethod
        }
    }
}