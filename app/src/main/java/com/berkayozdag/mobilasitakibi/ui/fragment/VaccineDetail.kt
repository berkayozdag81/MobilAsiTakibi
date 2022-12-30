package com.berkayozdag.mobilasitakibi.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
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
import com.google.firebase.firestore.FirebaseFirestore


class VaccineDetail : Fragment() {

    private lateinit var binding: FragmentVaccineDetailBinding
    private val adapter = DatesAdapter()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var vaccine: Vaccine
    private var tempDates: MutableList<Dates> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVaccineDetailBinding.inflate(inflater, container, false)
        setupRecyclerview()
        setListener()
        vaccine = arguments?.getSerializable("vaccine") as Vaccine
        binding.detailVaccineName.text = vaccine.name
        vaccine.dates?.let {
            loadDates(it)
            tempDates.addAll(it)
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
        adapter.onItemClicked = { position ->
            if (tempDates.isNotEmpty()) {

                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Aşınızı silmek ister misiniz?")
                builder.setPositiveButton("Evet") { dialog, _ ->
                    tempDates.removeAt(position)
                    db.collection("vaccines").document(vaccine.id).update("dates", tempDates)
                        .addOnSuccessListener {
                            binding.vaccineDatesRw.adapter = adapter
                            adapter.items = tempDates
                        }
                    dialog.dismiss()
                }
                builder.setNegativeButton("Hayır") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }

    private fun setListener() = with(binding) {
        arrowBack.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_vaccineDetail_to_homeFragment3)
        }
    }
}