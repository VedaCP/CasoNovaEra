package com.example.casonovaera

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.casonovaera.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    private val viewModelNovaEra: NovaEraViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NovaEraAdapter()
        binding.rvProduct.adapter = adapter
        binding.rvProduct.layoutManager = GridLayoutManager(context, 1)

        viewModelNovaEra.novaEraDataFromDB.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("LISTA", "$it")
                adapter.update(it)
            }
        })
        adapter.productsItem().observe(viewLifecycleOwner, {
            it?.let {
                val bundle = Bundle()
                bundle.putInt("Lista", it.id)
               // Log.d("CLAVE", "$it.id")
                viewModelNovaEra.getDetail(it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })

    }
}