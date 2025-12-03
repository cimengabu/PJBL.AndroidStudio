package com.Indah.aplikasipertama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class indah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indah)

        val etNama = findViewById<EditText>(R.id.etNama)

        val jumlah1 = findViewById<EditText>(R.id.jumlah1)
        val jumlah2 = findViewById<EditText>(R.id.jumlah2)
        val jumlah3 = findViewById<EditText>(R.id.jumlah3)
        val jumlah4 = findViewById<EditText>(R.id.jumlah4)

        val btnPesan = findViewById<Button>(R.id.btnPesan)

        btnPesan.setOnClickListener {

            val nama = etNama.text.toString()

            val j1 = jumlah1.text.toString().ifEmpty { "0" }.toInt()
            val j2 = jumlah2.text.toString().ifEmpty { "0" }.toInt()
            val j3 = jumlah3.text.toString().ifEmpty { "0" }.toInt()
            val j4 = jumlah4.text.toString().ifEmpty { "0" }.toInt()

            val i = Intent(this, NotaActivity::class.java)
            i.putExtra("nama", nama)
            i.putExtra("j1", j1)
            i.putExtra("j2", j2)
            i.putExtra("j3", j3)
            i.putExtra("j4", j4)

            startActivity(i)
        }
    }
}
