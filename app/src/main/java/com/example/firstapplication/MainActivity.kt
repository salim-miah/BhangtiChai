package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    private lateinit var taka: TextView
    private lateinit var fivehundred: TextView
    private lateinit var hundred: TextView
    private lateinit var fifty: TextView
    private lateinit var twenty: TextView
    private lateinit var ten: TextView
    private lateinit var five: TextView
    private lateinit var two: TextView
    private lateinit var one: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttons
        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)
        var button5 = findViewById<Button>(R.id.button5)
        var button6 = findViewById<Button>(R.id.button6)
        var button7 = findViewById<Button>(R.id.button7)
        var button8 = findViewById<Button>(R.id.button8)
        var button9 = findViewById<Button>(R.id.button9)
        var button0 = findViewById<Button>(R.id.button0)
        var clear = findViewById<Button>(R.id.clear)

        //taka textview
        taka = findViewById(R.id.taka)

        //notes
        fivehundred = findViewById(R.id.fivehundred)
        hundred = findViewById(R.id.hundred)
        fifty = findViewById(R.id.fifty)
        twenty = findViewById(R.id.twenty)
        ten = findViewById(R.id.ten)
        five = findViewById(R.id.five)
        two = findViewById(R.id.two)
        one = findViewById(R.id.one)

        //if buttons are clicked
        button0.setOnClickListener(){
            updateValue("0")
        }
        button1.setOnClickListener(){
            updateValue("1")
        }
        button2.setOnClickListener(){
            updateValue("2")
        }
        button3.setOnClickListener(){
            updateValue("3")
        }
        button4.setOnClickListener(){
            updateValue("4")
        }
        button5.setOnClickListener(){
            updateValue("5")
        }
        button6.setOnClickListener(){
            updateValue("6")
        }
        button7.setOnClickListener(){
            updateValue("7")
        }
        button8.setOnClickListener(){
            updateValue("8")
        }
        button9.setOnClickListener(){
            updateValue("9")
        }
        clear.setOnClickListener(){
            if (taka.text.length>1){
                taka.text = taka.text.substring(0,taka.text.length-1)
                calculateChange(taka.text.toString().toInt(),fivehundred,hundred,fifty,twenty,ten,five,two,one)
            }
            else if (taka.text.length==1){
                taka.text = ""
                calculateChange(0,fivehundred,hundred,fifty,twenty,ten,five,two,one)
            }
        }
    }

    //To save the data
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedtaka",taka.text.toString())
        outState.putString("fivehundred",fivehundred.text.toString())
        outState.putString("hundred",hundred.text.toString())
        outState.putString("fifty",fifty.text.toString())
        outState.putString("twenty",twenty.text.toString())
        outState.putString("ten",ten.text.toString())
        outState.putString("five",five.text.toString())
        outState.putString("two",two.text.toString())
        outState.putString("one",one.text.toString())
    }

    // To restore data
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        taka.text = savedInstanceState.getString("savedtaka")
        fivehundred.text = savedInstanceState.getString("fivehundred")
        hundred.text = savedInstanceState.getString("hundred")
        fifty.text = savedInstanceState.getString("fifty")
        twenty.text = savedInstanceState.getString("twenty")
        ten.text = savedInstanceState.getString("ten")
        five.text = savedInstanceState.getString("five")
        two.text = savedInstanceState.getString("two")
        one.text = savedInstanceState.getString("one")
    }

    fun calculateChange(taka: Int,fivehundred: TextView, hundred: TextView, fifty: TextView, twenty: TextView, ten: TextView, five: TextView, two: TextView, one: TextView){
        var notes = arrayOf(500,100,50,20,10,5,2,1)
        var count = arrayOf(0,0,0,0,0,0,0,0)
        var remainder: Double = taka.toDouble()

        for (i in 0..(notes.size)-1){
            count[i]+= (remainder/notes[i]).toInt()
            remainder = remainder%(notes[i])
        }

        for (i in 0..(count.size)-1){
            when (i){
                0 -> fivehundred.setText(count[i].toString())
                1 -> hundred.setText(count[i].toString())
                2 -> fifty.setText(count[i].toString())
                3 -> twenty.setText(count[i].toString())
                4 -> ten.setText(count[i].toString())
                5 -> five.setText(count[i].toString())
                6 -> two.setText(count[i].toString())
                7 -> one.setText(count[i].toString())
            }
        }
    }

    fun updateValue(value: String){
        if (taka.text.length<=8){
            taka.append(value)
            calculateChange(taka.text.toString().toInt(),fivehundred,hundred,fifty,twenty,ten,five,two,one)
        }else{
            Toast.makeText(this, "You cannot enter more than 9 digits", Toast.LENGTH_SHORT).show()
        }
    }
}