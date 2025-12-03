package com.Indah.aplikasipertama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class NotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        val tvNama = findViewById<TextView>(R.id.tvNama)
        val tvDetail = findViewById<TextView>(R.id.tvDetailPesanan)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        // Terima data dari Intent
        val nama = intent.getStringExtra("nama")
        val j1 = intent.getIntExtra("j1", 0)
        val j2 = intent.getIntExtra("j2", 0)
        val j3 = intent.getIntExtra("j3", 0)
        val j4 = intent.getIntExtra("j4", 0)

        // Harga Menu
        val h1 = 25000 // Nasi Bakar
        val h2 = 20000 // Ayam Kremes
        val h3 = 22000 // Sate Ayam
        val h4 = 15000 // Mie Goreng

        var detail = ""
        var total = 0

        if (j1 > 0) {
            detail += "Nasi Bakar x$j1 = ${rupiah(j1 * h1)}\n"
            total += j1 * h1
        }
        if (j2 > 0) {
            detail += "Ayam Kremes x$j2 = ${rupiah(j2 * h2)}\n"
            total += j2 * h2
        }
        if (j3 > 0) {
            detail += "Sate Ayam x$j3 = ${rupiah(j3 * h3)}\n"
            total += j3 * h3
        }
        if (j4 > 0) {
            detail += "Mie Goreng x$j4 = ${rupiah(j4 * h4)}\n"
            total += j4 * h4
        }

        tvNama.text = "Nama: $nama"
        tvDetail.text = detail
        tvTotal.text = "Total: ${rupiah(total)}"
    }

    // Format uang ke Rupiah (versi terbaru)
    private fun rupiah(nominal: Int): String {
        val localeID = Locale.forLanguageTag("id-ID")
        return NumberFormat.getCurrencyInstance(localeID).format(nominal)
    }
}
