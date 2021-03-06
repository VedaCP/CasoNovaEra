package com.example.casonovaera

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.casonovaera.databinding.FragmentFirstBinding
import com.example.casonovaera.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: NovaEraViewModel by activityViewModels()
    var idImage: Int = 0
    var name: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

/*    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            idImage = requireArguments().getInt("Lista")
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AdapterDetails()
        binding.rvDetails.adapter = adapter
        binding.rvDetails.layoutManager = GridLayoutManager(context, 1)

        viewModel.returnDetail(idImage).observe(viewLifecycleOwner,
        Observer {
            it?.let {
                Log.d("idImage", "$it")
                adapter.update(it)
            }
        })



    }
}