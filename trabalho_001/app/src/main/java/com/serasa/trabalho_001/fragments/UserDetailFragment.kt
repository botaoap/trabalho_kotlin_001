package com.serasa.trabalho_001.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.serasa.trabalho_001.R
import com.serasa.trabalho_001.enum.LinkApi
import com.serasa.trabalho_001.model.ResultApi
import com.serasa.trabalho_001.serviceRetrofit.Endpoint
import com.serasa.trabalho_001.serviceRetrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDetailFragment : Fragment(R.layout.fragment_random_user_detail), Callback<ResultApi> {

//    private lateinit var showRecyclerView : RecyclerView
//    private lateinit var adapter : AdapterListApi

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitBuilder
            .getConnectioRetrofit(LinkApi.RANDOM_USER.url)
            .create(Endpoint::class.java)
            .getUsers()
            .clone()
            .enqueue(this)
//
//        adapter = AdapterListApi(mutableListOf())
//
//        showRecyclerView = view.findViewById(R.id.recyclerViewUser)
//        showRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        showRecyclerView.adapter = adapter



    }

    fun bind(result : ResultApi) {
        val itemView = requireView()

        itemView.findViewById<TextView>(R.id.textViewTitle).apply {
            text = "${result.resultApi[0].name.title}. "
        }
        itemView.findViewById<TextView>(R.id.textViewFirstName).apply {
            text = "${result.resultApi[0].name.firstName} "
        }
        itemView.findViewById<TextView>(R.id.textViewLastName).apply {
            text = result.resultApi[0].name.lastName
        }
        itemView.findViewById<TextView>(R.id.textViewGender).apply {
            text = "Gender: ${result.resultApi[0].gender}"
        }
        itemView.findViewById<TextView>(R.id.textViewEmail).apply {
            text = result.resultApi[0].email
        }
        itemView.findViewById<TextView>(R.id.textViewCountry).apply {
            text = "Country: ${result.resultApi[0].location.country}"
        }
        itemView.findViewById<TextView>(R.id.textViewCity).apply {
            text = "City: ${result.resultApi[0].location.city}"
        }
        itemView.findViewById<TextView>(R.id.textViewState).apply {
            text = "State: ${result.resultApi[0].location.state}"
        }
        itemView.findViewById<TextView>(R.id.textViewStreetName).apply {
            text = "Street: ${result.resultApi[0].location.street.name}"
        }
        itemView.findViewById<TextView>(R.id.textViewStreetNumber).apply {
            text = result.resultApi[0].location.street.number.toString()
        }
        itemView.findViewById<ImageView>(R.id.imageViewUser).apply {
            Glide.with(context)
                .load(result.resultApi[0].picture.large)
                .placeholder(R.drawable.ic_error)
                .into(this)
        }

    }


    override fun onResponse(call: Call<ResultApi>, response: Response<ResultApi>) {
        println("Sucesso")
        response.body()?.apply {
            bind(this)
        }
    }

    override fun onFailure(call: Call<ResultApi>, t: Throwable) {
        println("Error")
    }
}