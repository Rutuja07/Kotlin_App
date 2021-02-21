package com.example.ageinminutes

import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)

        }
    }
    fun clickDatePicker(view: View){
            val myCalendar=Calendar.getInstance()
            val year=myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day= myCalendar.get(Calendar.DAY_OF_MONTH)
            val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view, year, selectedmonth, dayOfMonth ->
                Toast.makeText(this,"The chosen year is $year,the month is $selectedmonth and the day is $dayOfMonth",Toast.LENGTH_LONG).show()
                val selectedDate = "$dayOfMonth/${selectedmonth+1}/$year"
                tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate =  sdf.parse(selectedDate)
                val selectedDateInMinutes =theDate!!.time / 60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinute=currentDate!!.time/60000
                val differenceInMinutes=currentDateToMinute-selectedDateInMinutes
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            },year, month,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()







    }
}