package com.example.ghadeer_ss

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ghadeer_ss.API.APIClient
import com.example.ghadeer_ss.API.APIInterface
import com.example.ghadeer_ss.Adapter.RVAdapter
import com.example.ghadeer_ss.API.UniversityData
import com.example.ghadeer_ss.ViewModel.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiFragment : Fragment() {

    lateinit var etSearch: EditText
    lateinit var btSearch: Button
    lateinit var rvMain: RecyclerView
    lateinit var viewModel: ViewModel

    private lateinit var info: ArrayList<UniversityData.University>
    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_api, container, false)

        etSearch = view.findViewById(R.id.etSearch)
        btSearch = view.findViewById(R.id.btSearch)
        rvMain = view.findViewById(R.id.rvMain)
        info = arrayListOf()

        //...
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        rvMain.adapter = RVAdapter(info, this)
        rvMain.layoutManager = LinearLayoutManager(requireContext())

        btSearch.setOnClickListener {
            if (etSearch.text.isNotEmpty()) {

                requestAPI()
                etSearch.text.clear()
                etSearch.clearFocus()
                info.clear()

            } else {
                Toast.makeText(activity, "please add a name", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }


    private fun requestAPI() {
        apiInterface?.getUniversityData(etSearch.text.toString())
            ?.enqueue(object : Callback<UniversityData?> {
                override fun onResponse(
                    call: Call<UniversityData?>,
                    response: Response<UniversityData?>
                ) {
                    for (university in response.body()!!) {
                        info.add(university)
                    }
                    rvMain.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<UniversityData?>, t: Throwable) {
                    Toast.makeText(activity, "" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun addToDatabase(name: String, country: String, note: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("ADD", DialogInterface.OnClickListener {

                    _, _ ->
                run {
                    viewModel.addUniversityDB(name, country, note)
                    Toast.makeText(activity, "University added", Toast.LENGTH_LONG).show()
                }
            })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Add to database")
        alert.show()
    }

}