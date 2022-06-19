package com.example.agebybirth

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    var selected : TextView? = null
    var minutes : Long = 0
    var age: TextView? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnDate)
        selected =  findViewById(R.id.tvSelected)
        age = findViewById(R.id.age)
        btn.setOnClickListener{
            val mycalender= Calendar.getInstance()

            var year = mycalender.get(Calendar.YEAR)
            val month = mycalender.get(Calendar.MONTH)
            val day = mycalender.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this,
                { _, syear, smonth, sday->
                    selected?.text = "$sday/${smonth+1}/$syear"
                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

//                    val diff: Long = date1.getTime() - date2.getTime()
//                    val seconds = diff / 1000
//                    val minutes = seconds / 60
//                    val hours = minutes / 60
//                    val days = hours / 24
                    //val thedate = sdf.parse("$sday/${smonth+1}/$syear")
                    if(month<smonth)
                        year-=1

                    minutes = ((year-syear)*525600.toLong()) + (abs(month-smonth)*43800) + ((day-sday)*1440);
                    Toast.makeText(this,"$minutes",Toast.LENGTH_LONG).show()
                    age?.text = "${year-syear} years,${abs(month-smonth)} months, ${day-sday} days"
                },
                year,
                month,
                day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()

        }
    }
}