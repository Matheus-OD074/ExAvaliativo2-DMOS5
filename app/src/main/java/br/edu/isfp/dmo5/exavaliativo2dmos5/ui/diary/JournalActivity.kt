package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.diary

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import br.edu.isfp.dmo5.exavaliativo2dmos5.R
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ActivityJournalBinding
import java.time.LocalDate
import java.time.LocalDateTime

class JournalActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityJournalBinding
    private lateinit var viewModel: JournalViewModel
    private var date: LocalDateTime = LocalDateTime.now()
    private var time: LocalDateTime = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
        setUpObservers()
      //  setUpUiDate()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMounth: Int) {
        date = LocalDateTime.of(year, month+1, dayOfMounth, 0, 0)
        setUpUiDate()
    }

    private fun setUpUiDate(){
        val str = "${date.dayOfMonth}/${date.month}/${date.year}"
        binding.btnDate.text = str
    }

    private fun setUpListeners(){
        binding.btnDate.setOnClickListener{
            initDatePickerDialog()
        }
    }

    private fun setUpObservers(){
        viewModel.inserted.observe(this, Observer {
            if (it){
                Toast.makeText(this, R.string.insert_sucess, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, R.string.insert_failure, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initDatePickerDialog(){
        DatePickerDialog(this,this, date.year, date.monthValue-1, date.dayOfMonth).show()
    }

}