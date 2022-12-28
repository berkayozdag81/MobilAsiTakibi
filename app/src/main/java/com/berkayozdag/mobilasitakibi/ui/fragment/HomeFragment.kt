package com.berkayozdag.mobilasitakibi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayozdag.mobilasitakibi.adapter.VaccineAdapter
import com.berkayozdag.mobilasitakibi.databinding.FragmentHomeBinding
import com.berkayozdag.mobilasitakibi.model.User
import com.berkayozdag.mobilasitakibi.model.Vaccine
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter = VaccineAdapter()

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //setupRecyclerview()
        loadVaccines()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
        //loadVaccines()
        //addPersonal()
    }

    /*private fun addPersonal() {
        db.collection("users")
            .add(Deneme("Berkay", "Özdağ"))
            .addOnSuccessListener { documentReference ->
                //Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                Toast.makeText(this.context, "Hata: ${documentReference.id}", Toast.LENGTH_LONG)
                    .show()
            }
            .addOnFailureListener { e ->
                //Log.w(TAG, "Error adding document", e)
                Toast.makeText(this.context, "Hata: ${e}", Toast.LENGTH_LONG).show()
            }
    }*/

    private fun initViews() {
    }

    /*private fun setupRecyclerview() = with(binding) {
        val layoutManager = LinearLayoutManager(requireContext())
        vaccineRw.layoutManager = layoutManager
    }*/

    private fun loadVaccines() = with(binding) {

        db.collection("deneme").get().addOnSuccessListener {
            Toast.makeText(requireContext(), "Veri Çekme başarılı", Toast.LENGTH_LONG).show()
            val list: List<User> = emptyList()
            val layoutManager = LinearLayoutManager(requireContext())
            vaccineRw.layoutManager = layoutManager
            if (!it.isEmpty()) {
                for (snapshot in it) list.plus(
                    snapshot.toObject(
                        User::class.java
                    )
                )
            }

            adapter.items = list
            vaccineRw.adapter = adapter

        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Veri Çekme Başarısız", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListener() {

    }
}