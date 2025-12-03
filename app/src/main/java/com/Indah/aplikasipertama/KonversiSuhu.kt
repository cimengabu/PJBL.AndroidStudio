package com.Indah.aplikasipertama

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class KonversiSuhu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konversi_suhu)

        // Ambil ID dari XML
        val spSuhu = findViewById<Spinner>(R.id.spSuhu)
        val spSuhu1 = findViewById<Spinner>(R.id.spSuhu1)
        val etInput = findViewById<EditText>(R.id.input)
        val etOutput = findViewById<EditText>(R.id.Output)
        val btnKonversi = findViewById<Button>(R.id.btnkonverter)

        btnKonversi.setOnClickListener {
            val nilai = etInput.text.toString()

            if (nilai.isEmpty()) {
                Toast.makeText(this, "Masukkan angka terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val angka = nilai.toDouble()
            val dari = spSuhu.selectedItem.toString()
            val ke = spSuhu1.selectedItem.toString()

            val hasil = konversiSuhu(angka, dari, ke)
            etOutput.setText(hasil.toString())
        }
    }

         
    private fun konversiSuhu(nilai: Double, dari: String, ke: String): Double {

        // Ubah ke Celsius dulu
        val celsius = when (dari) {
            "Celsius" -> nilai
            "Fahrenheit" -> (nilai - 32) * 5 / 9
            "Kelvin" -> nilai - 273.15
            else -> nilai
        }

        // Ubah dari Celsius ke satuan tujuan
        return when (ke) {
            "Celsius" -> celsius
            "Fahrenheit" -> celsius * 9 / 5 + 32
            "Kelvin" -> celsius + 273.15
            else -> celsius
        }
    }
}
