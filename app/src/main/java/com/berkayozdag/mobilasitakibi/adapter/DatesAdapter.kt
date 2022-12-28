package com.berkayozdag.mobilasitakibi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.berkayozdag.mobilasitakibi.databinding.RowVaccineDateBinding
import com.berkayozdag.mobilasitakibi.model.Dates
import com.google.firebase.firestore.core.View
import java.util.*

class DatesAdapter(
    var onItemClicked: ((Int) -> Unit) = {},
) :
    RecyclerView.Adapter<DatesAdapter.DatesViewHolder>() {

    var items: List<Dates> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class DatesViewHolder(private val binding: RowVaccineDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dates: Dates,position: Int) {

            binding.vaccineDate.text=dates.vaccineDate
            binding.selim.setOnClickListener {
                onItemClicked(position)
            }
            /*
            if(!dates.hasTaken){
                binding.vaccineDate.visibility=android.view.View.GONE
                binding.vaccineHasTaken.visibility=android.view.View.GONE
            }else{
                binding.vaccineDate.text=dates.vaccineDate
                binding.vaccineHasTaken.visibility=android.view.View.VISIBLE
            }

             */

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesViewHolder {
        val binding =
            RowVaccineDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DatesViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount() = items.size

}