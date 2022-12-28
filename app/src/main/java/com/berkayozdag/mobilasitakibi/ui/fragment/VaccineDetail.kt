package com.berkayozdag.mobilasitakibi.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayozdag.mobilasitakibi.R
import com.berkayozdag.mobilasitakibi.adapter.DatesAdapter
import com.berkayozdag.mobilasitakibi.databinding.FragmentVaccineDetailBinding
import com.berkayozdag.mobilasitakibi.model.Dates
import com.berkayozdag.mobilasitakibi.model.Vaccine
import com.berkayozdag.mobilasitakibi.utils.showToast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class VaccineDetail : Fragment() {

    private lateinit var binding: FragmentVaccineDetailBinding
    private val adapter = DatesAdapter()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var vaccine:Vaccine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVaccineDetailBinding.inflate(inflater, container, false)
        setupRecyclerview()
        vaccine=arguments?.getSerializable("vaccine") as Vaccine
        context?.showToast(vaccine.id)
        binding.detailVaccineName.text=vaccine.name
        vaccine.dates?.let { loadDates(it) }
        binding.floatingActionButton2.setOnClickListener {
            context?.showToast(vaccine.dates?.size.toString())
        }
        return binding.root
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.vaccineDatesRw.layoutManager = layoutManager
    }

    private fun loadDates(dates: List<Dates>) {
        binding.vaccineDatesRw.adapter = adapter
        adapter.items = dates
        adapter.onItemClicked={
            dates.drop(it)
            adapter.notifyDataSetChanged()
        }
    }




}