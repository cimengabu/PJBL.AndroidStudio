package com.Indah.aplikasipertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Kalku : AppCompatActivity() {

    private lateinit var display: EditText
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        display = findViewById(R.id.TEXT)

        // Listener angka
        setNumberListener(R.id.btn0)
        setNumberListener(R.id.btn1)
        setNumberListener(R.id.btn2)
        setNumberListener(R.id.btn3)
        setNumberListener(R.id.btn4)
        setNumberListener(R.id.btn5)
        setNumberListener(R.id.btn6)
        setNumberListener(R.id.btn7)
        setNumberListener(R.id.btn8)
        setNumberListener(R.id.btn9)

        // Listener operator
        setOperatorListener(R.id.btnPlus)
        setOperatorListener(R.id.btnminus)
        setOperatorListener(R.id.btnDevide)
        setOperatorListener(R.id.btnX)

        // Tombol titik
        findViewById<Button>(R.id.btnPersen).setOnClickListener {
            if (lastNumeric && !lastDot) {
                display.append(".")
                lastNumeric = false
                lastDot = true
            }
        }

        // Tombol clear
        findViewById<Button>(R.id.btnC).setOnClickListener {
            display.setText("")
            lastNumeric = false
            stateError = false
            lastDot = false
        }

        // Tombol delete
        findViewById<Button>(R.id.btnDelate).setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                display.setText(text.dropLast(1))
                lastNumeric = text.last().isDigit()
            }
        }

        // Tombol sama dengan
        findViewById<Button>(R.id.btnequal).setOnClickListener {
            onEqual()
        }
    }

    private fun setNumberListener(id: Int) {
        findViewById<Button>(id).setOnClickListener {
            display.append((it as Button).text)
            lastNumeric = true
        }
    }

    private fun setOperatorListener(id: Int) {
        findViewById<Button>(id).setOnClickListener {
            if (lastNumeric && !stateError) {
                display.append((it as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun onEqual() {
        if (lastNumeric && !stateError) {
            try {
                val input = display.text.toString().replace("X", "*")
                val result = eval(input)
                display.setText(result.toString())
            } catch (e: Exception) {
                display.setText("Error")
                stateError = true
                lastNumeric = false
            }
        }
    }

    // Fungsi hitung utama (Parser)
    private fun eval(input: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < input.length) input[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < input.length) throw RuntimeException("Unexpected: " + input[pos])
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if ((ch in '0'.code..'9'.code) || ch == '.'.code) {
                    while ((ch in '0'.code..'9'.code) || ch == '.'.code) nextChar()
                    x = input.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + input[pos])
                }
                return x
            }
        }.parse()
    }
}
