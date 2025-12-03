package com.Indah.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {

    private lateinit var cardForm: CardView
    private lateinit var cardProfil: CardView
    private lateinit var cardKalkulator: CardView
    private lateinit var cardMakanan: CardView
    private lateinit var cardTemperature: CardView
    private lateinit var cardKeluar: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_card)
        enableEdgeToEdge()

        init()

    }

    private fun init() {
        cardForm = findViewById(R.id.CardForm)
        cardProfil = findViewById(R.id.CardProfil)
        cardKalkulator = findViewById(R.id.CardKalkulator)
        cardMakanan = findViewById(R.id.CardMakanan)
        cardTemperature = findViewById(R.id.CardTemperature)
        cardKeluar = findViewById(R.id.CardKeluar)

        setCardListeners()
    }

    private fun setCardListeners() {
        cardForm.setOnClickListener {
            Toast.makeText(this, "Card View Form Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RelatifActivity::class.java))
        }

        cardProfil.setOnClickListener {
            Toast.makeText(this, "Card View Profil Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,Profil::class.java))
        }

        cardKalkulator.setOnClickListener {
            Toast.makeText(this, "Card View Kalkulator Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Kalkulator::class.java))
        }

        cardMakanan.setOnClickListener {
            Toast.makeText(this, "Card View Makanan Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, indah::class.java)) // Pastikan nama class benar!
        }

        cardTemperature.setOnClickListener {
            Toast.makeText(this, "Card View Temperature Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, KonversiSuhu::class.java))
        }

        cardKeluar.setOnClickListener {
            showExitDialog()
        }
    }


    private fun showExitDialog() {
        val dialogView = layoutInflater.inflate(R.layout.activity_exit, null)
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialogView.findViewById<Button>(R.id.btnYes).setOnClickListener {
            finishAffinity()
        }
        dialogView.findViewById<Button>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
