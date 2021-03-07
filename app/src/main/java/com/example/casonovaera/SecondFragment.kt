package com.example.casonovaera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.casonovaera.databinding.FragmentFirstBinding
import com.example.casonovaera.databinding.FragmentSecondBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: NovaEraViewModel by activityViewModels()
    var idImage: Int = 0
    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            idImage = requireArguments().getInt("Lista")
            name = requireArguments().getString("Name", "$id")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var adapter = AdapterDetails()
        binding.rvDetails.adapter = adapter
        binding.rvDetails.layoutManager = GridLayoutManager(context, 1)

        viewModel.returnDetail(idImage).observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("idImage", "$it")
                adapter.update(it)
            }
        })

        binding.fabVenta.setOnClickListener {
            sendEmail()
        }
    }

        fun sendEmail(){
            val para = arrayOf("info@novaera.cl")
            val copia = arrayOf("")
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, para)
            emailIntent.putExtra(Intent.EXTRA_CC, copia)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.asunto, name, idImage))
            emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.texto, name, idImage))
            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(context,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show()
            }
        }
}