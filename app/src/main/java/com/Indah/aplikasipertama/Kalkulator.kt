package com.Indah.aplikasipertama

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Kalkulator : AppCompatActivity() {

    private lateinit var display: EditText
    private var input = ""
    private var operator = ""
    private var value1 = 0.0
    private var value2 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        display = findViewById(R.id.TEXT)

        val btnNumbers = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
        )

        // Tombol angka ditekan → tampil
        for (id in btnNumbers) {
            findViewById<Button>(id).setOnClickListener {
                val text = (it as Button).text.toString()
                input += text
                display.setText(input)
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnminus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnX).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDevide).setOnClickListener { setOperator("/") }

        // tombol persen
        findViewById<Button>(R.id.btnPersen).setOnClickListener {
            if (input.isNotEmpty()) {
                val result = input.toDouble() / 100
                displayFormatted(result)
                input = display.text.toString()
            }
        }

        // tombol sama dengan
        findViewById<Button>(R.id.btnequal).setOnClickListener {
            if (input.isNotEmpty() && operator.isNotEmpty()) {
                value2 = input.toDouble()
                val result = when (operator) {
                    "+" -> value1 + value2
                    "-" -> value1 - value2
                    "*" -> value1 * value2
                    "/" -> if (value2 != 0.0) value1 / value2 else Double.NaN
                    else -> 0.0
                }

                displayFormatted(result)
                input = display.text.toString()
                operator = ""
            }
        }

        // tombol clear
        findViewById<Button>(R.id.btnC).setOnClickListener {
            input = ""
            operator = ""
            display.setText("0")
        }

        // tombol delete 1 digit
        findViewById<Button>(R.id.btnDelate).setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                display.setText(if (input.isEmpty()) "0" else input)
            }
        }
    }

    private fun setOperator(op: String) {
        if (input.isNotEmpty()) {
            value1 = input.toDouble()
            operator = op
            input = ""
        }
    }

    // Format hasil supaya tidak muncul .0 kalau hasil bilangan bulat
    private fun displayFormatted(result: Double) {
        val text = if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        display.setText(text)
    }
}
