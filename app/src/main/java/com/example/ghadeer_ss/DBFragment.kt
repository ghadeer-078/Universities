package com.example.ghadeer_ss

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghadeer_ss.API.APIClient
import com.example.ghadeer_ss.API.APIInterface
import com.example.ghadeer_ss.API.UniversityData
import com.example.ghadeer_ss.Adapter.RVDdapter
import com.example.ghadeer_ss.Database.UN
import com.example.ghadeer_ss.ViewModel.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DBFragment : Fragment() {

    lateinit var viewModel: ViewModel
    lateinit var rvAdapter: RVDdapter
    lateinit var rvFav: RecyclerView

    private lateinit var info: ArrayList<UniversityData.University>
    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_d_b, container, false)

        rvFav = view.findViewById(R.id.rvFav)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getUniversityDB().observe(viewLifecycleOwner, { show ->
            rvAdapter.update(show)
        }
        )
        updateRV()

        return view
    }

    fun updateRV() {
        rvAdapter = RVDdapter(this)
        rvFav.adapter = rvAdapter
        rvFav.layoutManager = LinearLayoutManager(requireContext())

    }

    fun updateNote(currentNote: String, id: Int) {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        val etNote = EditText(requireContext())
        etNote.hint = "enter notes"

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {

                    _, _ ->
                run {
                    viewModel.updateUniversityDB("${etNote.text}", id)
                    Toast.makeText(activity, "Notes updated", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Notes")
        alert.setView(etNote)
        alert.show()
    }

}