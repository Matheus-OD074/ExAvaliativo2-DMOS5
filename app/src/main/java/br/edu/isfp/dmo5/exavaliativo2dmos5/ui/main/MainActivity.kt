package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.main

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.isfp.dmo5.exavaliativo2dmos5.R
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ActivityMainBinding
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.adapter.DairyAdapter
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.listener.JournalClickListener

class MainActivity : AppCompatActivity(), View.OnClickListener, JournalClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = DairyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpRecyclerView()
        setUpListeners()
    }

    override fun onClick(view: View?) {
        if (view.id == R.id.button_add){

        }
    }

    override fun clickDelete(position: Int) {
        viewModel.handleDelete(position)
    }

    private fun setUpRecyclerView(){
        binding.recyclerJournal.layoutManager = LinearLayoutManager(this)
        binding.recyclerJournal.adapter = adapter
    }

    private fun setUpListeners(){
        binding.buttonAdd.setOnClickListener(this)
    }

}