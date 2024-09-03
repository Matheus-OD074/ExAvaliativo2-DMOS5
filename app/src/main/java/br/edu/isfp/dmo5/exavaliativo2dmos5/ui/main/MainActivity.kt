package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.isfp.dmo5.exavaliativo2dmos5.R
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ActivityMainBinding
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.adapter.JournalAdapter
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.diary.JournalActivity
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.listener.JournalClickListener
import br.edu.isfp.dmo5.exavaliativo2dmos5.util.Constant

class MainActivity : AppCompatActivity(), JournalClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = JournalAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(JournalRepository(applicationContext))
        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)

        setUpRecyclerView()
        setUpListeners()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun clickDelete(position: Int) {
        val journal = adapter.getDatasetItem(position)
        viewModel.deleteJournal(journal.id)
    }

    override fun clickOpen(position: Int) {
        val journal = adapter.getDatasetItem(position)
        val mIntent = Intent(this, JournalActivity::class.java)
        mIntent.putExtra(Constant.JOURNAL_ID, journal.id)
        startActivity(mIntent)
    }


    private fun setUpRecyclerView(){
        binding.recyclerJournal.layoutManager = LinearLayoutManager(this)
        binding.recyclerJournal.adapter = adapter
    }

    private fun setUpListeners(){
        binding.buttonAdd.setOnClickListener{
            val mIntent = Intent(this, JournalActivity::class.java)
            startActivity(mIntent)
        }
    }

    private fun setUpObservers(){
        viewModel.journals.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }

}