package com.berkayozdag.mobilasitakibi.ui.fragment.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayozdag.mobilasitakibi.R
import com.berkayozdag.mobilasitakibi.adapter.VaccineAdapter
import com.berkayozdag.mobilasitakibi.databinding.FragmentHome2Binding
import com.berkayozdag.mobilasitakibi.databinding.FragmentHomeBinding
import com.berkayozdag.mobilasitakibi.model.Vaccine
import com.berkayozdag.mobilasitakibi.utils.showToast
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHome2Binding
    private val adapter = VaccineAdapter()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVaccines()
        setListener()
    }


    private fun loadVaccine(vaccines: List<Vaccine>) {
        adapter.items = vaccines
        binding.vaccinesRW.adapter = adapter
        adapter.onItemClicked = { vaccine ->
            val bundle = Bundle()
            bundle.putSerializable("vaccine", vaccine)
            findNavController().navigate(R.id.action_homeFragment3_to_vaccineDetail, bundle)
        }
    }

    private fun getVaccines() {
        db.collection("vaccines").get().addOnSuccessListener {
            val vaccines = arrayListOf<Vaccine>()
            for (x in it.documents) {
                Log.d("deneme", x.id)
                val vaccine = x.toObject(Vaccine::class.java)
                if (vaccine != null) {
                    if(vaccine.dates?.size !=0){
                        vaccines.add(vaccine)
                    }
                }
            }
            loadVaccine(vaccines)

        }.addOnFailureListener {
            context?.showToast("Veri cekme basarisiz")
        }
    }

    private fun setListener() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_addVaccineFragment4)
        }
    }
}