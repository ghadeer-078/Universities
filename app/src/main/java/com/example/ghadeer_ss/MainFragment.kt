package com.example.ghadeer_ss

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.btBrowse).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_apiFragment)
//            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_apiFragment)

        }

        view.findViewById<Button>(R.id.btLocalDB).setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_DBFragment)
//            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_DBFragment)

        }

        return view
    }


}