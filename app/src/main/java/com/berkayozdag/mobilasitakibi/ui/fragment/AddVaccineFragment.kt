package com.berkayozdag.mobilasitakibi.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.futured.donut.DonutSection
import com.berkayozdag.mobilasitakibi.R
import com.berkayozdag.mobilasitakibi.databinding.FragmentAddVaccineBinding
import com.berkayozdag.mobilasitakibi.databinding.FragmentHomeBinding
import com.berkayozdag.mobilasitakibi.model.Dates
import com.berkayozdag.mobilasitakibi.model.Vaccine
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AddVaccineFragment : Fragment() {
    private lateinit var binding: FragmentAddVaccineBinding

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentAddVaccineBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
    }

    private fun addVaccine(vaccine : Vaccine) {
        db.collection("vaccines")
            .add(vaccine)
            .addOnSuccessListener {
                db.collection("vaccines").document(it.id).update("id", it.id)
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Aşı Eklendi")
                builder.setPositiveButton("Tamam"){ dialog, _ ->
                    dialog.dismiss()
                    findNavController().navigate(R.id.action_addVaccineFragment4_to_homeFragment3)
                }
                builder.show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this.context, "Hata: ${e}", Toast.LENGTH_LONG).show()
            }
    }

    private fun initViews() = with(binding) {
    }

    @SuppressLint("SimpleDateFormat")
    private fun setListener() = with(binding){
        addVaccine.setOnClickListener {
            val list = arrayListOf<Dates>()

            val kacKere= editMedCountOfDay.text.toString().toInt()
            val kacGun= editMedManyTimesOfDay.text.toString().toInt()
            val cal=Calendar.getInstance()
            for(x in 1..kacGun){
                val formatter = SimpleDateFormat("dd-MM-yyyy")
                val formattedDate = formatter.format(cal.time)
                cal.add(Calendar.DATE, 1);
                for (x in 1..kacKere){
                    list.add(Dates(formattedDate,false))
                }
            }
            addVaccine(Vaccine("1",editMedName.text.toString(),list))
        }

        arrowBackAddVaccine.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_addVaccineFragment4_to_homeFragment3)
        }
    }
}