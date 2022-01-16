package com.example.ghadeer_ss.API

class UniversityData : ArrayList<UniversityData.University>(){

    data class University(
        val alpha_two_code: String,
        val country: String,
        val domains: List<String>,
        val name: String,
        val web_pages: List<String>
    )

}
