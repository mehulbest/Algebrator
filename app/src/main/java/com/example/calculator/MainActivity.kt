package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMultiply: Button
    lateinit var btnDivision: Button
    lateinit var btnClearAll: Button
    lateinit var btnAlgebra: Button
    lateinit var etA: EditText
    lateinit var etB: EditText
    lateinit var resultTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_subtraction)
        btnMultiply = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        btnClearAll = findViewById(R.id.btn_clear_all)
        btnAlgebra = findViewById(R.id.btn_algebra)
        etA = findViewById(R.id.et_a)
        etB = findViewById(R.id.et_b)
        resultTv = findViewById(R.id.result_tv)

        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnClearAll.setOnClickListener(this)
        btnAlgebra.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var result = 0.0
        when (v?.id) {
            R.id.btn_add -> {
                val a = etA.text.toString().toDoubleOrNull()
                val b = etB.text.toString().toDoubleOrNull()
                if (a == null || b == null) {
                    resultTv.text = "Please enter valid numbers"
                    return
                }
                result = a + b
            }
            R.id.btn_subtraction -> {
                val a = etA.text.toString().toDoubleOrNull()
                val b = etB.text.toString().toDoubleOrNull()
                if (a == null || b == null) {
                    resultTv.text = "Please enter valid numbers"
                    return
                }
                result = a - b
            }
            R.id.btn_multiplication -> {
                val a = etA.text.toString().toDoubleOrNull()
                val b = etB.text.toString().toDoubleOrNull()
                if (a == null || b == null) {
                    resultTv.text = "Please enter valid numbers"
                    return
                }
                result = a * b
            }
            R.id.btn_division -> {
                val a = etA.text.toString().toDoubleOrNull()
                val b = etB.text.toString().toDoubleOrNull()
                if (a == null || b == null) {
                    resultTv.text = "Please enter valid numbers"
                    return
                }
                result = a / b
            }
            R.id.btn_clear_all -> {
                etA.text.clear()
                etB.text.clear()
                resultTv.text = "Result"
            }
            R.id.btn_algebra -> {
                val intent = Intent(this, AlgebraActivity::class.java)
                startActivity(intent)
            }
        }
        if (v?.id != R.id.btn_clear_all && v?.id != R.id.btn_algebra) {
            resultTv.text = "Result is $result"
        }
    }
}
