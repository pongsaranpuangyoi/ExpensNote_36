package com.example.expensnote_36

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnClear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotalToday: TextView

    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)
        tvTotalToday = findViewById<TextView>(R.id.tvTotalToday)

        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินและรายการ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            total += amount

            val showNote = if (noteText.isNotEmpty()) noteText else "ไมระบุรายการ"

            tvLastItem.text = "รายการล่าสุด: $showNote - %.2f".format(amount)
            tvTotalToday.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()
        }
        btnClear.setOnClickListener {
            total = 0.0
            etAmount.text.clear()
            etNote.text.clear()
            tvLastItem.text = "รายการล่าสุด: -"
            tvTotalToday.text = "ยอดรวมวันนี้: 0.00 บาท"
        }
    }
}