package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.diary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ActivityJournalBinding
import br.edu.isfp.dmo5.exavaliativo2dmos5.util.Constant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JournalActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityJournalBinding
    private lateinit var viewModel: JournalViewModel
    private var dateTime: LocalDateTime = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = JournalViewModelFactory(JournalRepository(applicationContext))
        viewModel = ViewModelProvider(this, factory).get(JournalViewModel::class.java)

        handleBundle()
        setUpListeners()
        setUpObservers()
        setUpUiDate()
        setUpUiTime()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMounth: Int) {
        dateTime = LocalDateTime.of(year, month+1, dayOfMounth, 0, 0)
        setUpUiDate()
    }

    private fun handleBundle(){
        if (intent.hasExtra(Constant.JOURNAL_ID)){
            val id = intent.getLongExtra(Constant.JOURNAL_ID, -1)
            viewModel.showEvent(id)
        }
    }

    private fun setUpUiDate(){
        val str = "${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}"
        binding.btnDate.text = str
    }

    private fun setUpUiTime(){
        val str = "${dateTime.hour}:${dateTime.minute}"
        binding.btnTime.text = str
    }

    private fun setUpListeners(){
        binding.btnDate.setOnClickListener{
            initDatePickerDialog()
        }
        binding.btnSave.setOnClickListener {
            var title = binding.editTextTitle.text.toString()
            var desc = binding.textfieldDescription.text.toString()
            if (title.isEmpty() || title.isBlank()){
                Toast.makeText(this, "Título do diario é obrigatório",
                    Toast.LENGTH_SHORT).show()
            }else {
                viewModel.saveJournal(title, desc, dateTime)
            }
        }
    }

    private fun setUpObservers(){
        viewModel.saved.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Diario salvo com sucesso.",
                    Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao salvar diario.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isUpdate.observe(this, Observer {
            if (it){
                binding.btnSave.text = "salvar alteracao"
            }
        })
        viewModel.title.observe(this, Observer {
            binding.editTextTitle.setText(it)
        })
        viewModel.description.observe(this, Observer {
            binding.textfieldDescription.setText(it)
        })
        viewModel.dateTimeString.observe(this, Observer {
            val formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatterTime = DateTimeFormatter.ofPattern("hh:mm")
            Log.e("ERO", "FOI ?")
            dateTime = LocalDateTime.parse(it, formatterDate)
            Log.e("ERO", "FOI ?")
            setUpUiDate()
        })
    }

    private fun initDatePickerDialog(){
        DatePickerDialog(this,this, dateTime.year, dateTime.monthValue-1, dateTime.dayOfMonth).show()
    }

}