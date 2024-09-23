package com.example.lab5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private var calculatedValue: Double = 0.0 // Variable to store the calculated value

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rb1: RadioButton = findViewById(R.id.radioButton4)
        val rb2: RadioButton = findViewById(R.id.radioButton5)
        val rb3: RadioButton = findViewById(R.id.radioButton6)
        val text1: TextView = findViewById(R.id.textView5)
        val res: TextView = findViewById(R.id.textView3)
        val button: Button = findViewById(R.id.button)
        val numb: TextInputEditText = findViewById(R.id.num)
        val seekBar: SeekBar = findViewById(R.id.seekBar)

        seekBar.max = 100

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                text1.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        button.setOnClickListener {
            val numberInput = numb.text.toString().toDoubleOrNull()
            if (numberInput != null) {
                val discountPercentage = seekBar.progress.toDouble() / 100.0
                calculatedValue = when {
                    rb1.isChecked -> numberInput * 75 * (1 - discountPercentage)
                    rb2.isChecked -> numberInput * 90 * (1 - discountPercentage)
                    rb3.isChecked -> numberInput * 100 * (1 - discountPercentage)
                    else -> 0.0
                }
                res.text = String.format("%.2f руб.", calculatedValue)
            } else {
                res.text = "Invalid input"
            }
        }
    }
}