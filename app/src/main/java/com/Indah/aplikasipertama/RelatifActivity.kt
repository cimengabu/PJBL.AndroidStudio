package com.Indah.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RelatifActivity : AppCompatActivity() {
    //membuat variabel
    private lateinit var etNama: EditText
    private lateinit var etNomor: EditText
    private lateinit var etAlamat: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rgKelamin: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbMakan: CheckBox
    private lateinit var cbTidur: CheckBox
    private lateinit var cbOlahraga: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatif)
        //memanggil init
        init()

        //jika tombol simpan di clik akan melakukan
        btnSimpan.setOnClickListener {
            // membuat variabel nama lalu mengisinya dengan nilai yg ada di  id etNama
            val nama = etNama.text.toString()
            val nomor = etNomor.text.toString()
            val alamat = etAlamat.text.toString()
            val kelamin = rgKelamin.checkedRadioButtonId.toString()
            val agama = spAgama.selectedItem.toString()
            val hobi = mutableListOf<String>()
            if (cbMembaca.isChecked) hobi.add("Membaca")
            if (cbMakan.isChecked) hobi.add("Makan")
            if (cbTidur.isChecked) hobi.add("Tidur")
            if (cbOlahraga.isChecked) hobi.add("Olahraga")

            val hobiString = hobi.joinToString(", ")


            //untuk pergi ke halaman HasilFormActivity
            val keHasil = Intent(this, HasilForm::class.java)

            //menambahkan nilai baru ke variabel intent
            keHasil.putExtra("Nama", nama)
            keHasil.putExtra("Nomor", nomor)
            keHasil.putExtra("Alamat", alamat)
            keHasil.putExtra("Kelamin",kelamin)
            keHasil.putExtra("Agama",agama)
            keHasil.putExtra("Hobi", hobiString)
            startActivity(keHasil)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //membuat fun bernama init
    private fun init() {
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNomor = findViewById(R.id.etNomor)
        btnSimpan = findViewById(R.id.btnSimpan)
        rgKelamin = findViewById(R.id.rgKelamin)
        spAgama = findViewById(R.id.spAgama)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbOlahraga = findViewById(R.id.cbOlahraga)


    }
}