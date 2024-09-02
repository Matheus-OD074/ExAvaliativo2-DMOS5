package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.isfp.dmo5.exavaliativo2dmos5.R
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ActivityMainBinding
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.adapter.JournalAdapter
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.listener.JournalClickListener

class MainActivity : AppCompatActivity(), View.OnClickListener, JournalClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = JournalAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpRecyclerView()
        setUpListeners()
        setUpObservers()
    }

    override fun onClick(view: View) {
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

    private fun setUpObservers(){
        viewModel.journals.observe(this, Observer { journals ->
            adapter.submitDataset(journals)
            adapter.notifyDataSetChanged()
        })

        viewModel.inserted.observe(this, Observer {
            val str =  if(it){
                R.string.insert_sucess
            }else{
                R.string.insert_failure
            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        })

        viewModel.removed.observe(this, Observer {
            val str =  if(it){
                R.string.remove_sucess
            }else{
                R.string.remove_failure
            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        })
    }

}