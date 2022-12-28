package com.berkayozdag.mobilasitakibi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkayozdag.mobilasitakibi.databinding.RowVaccineBinding
import com.berkayozdag.mobilasitakibi.model.Vaccine

class VaccineAdapter(
    var onItemClicked: ((Vaccine) -> Unit) = {},
) :
    RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder>() {

    var items: List<Vaccine> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class VaccineViewHolder(private val binding: RowVaccineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vaccine: Vaccine) {

            binding.vaccineName.text = vaccine.name
            binding.vaccine.setOnClickListener {
                onItemClicked.invoke(vaccine)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineViewHolder {
        val binding =
            RowVaccineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VaccineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VaccineViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}