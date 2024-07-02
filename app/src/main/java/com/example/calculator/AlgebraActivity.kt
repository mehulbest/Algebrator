package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import net.objecthunter.exp4j.ExpressionBuildergit

class AlgebraActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var etEquation: EditText
    lateinit var btnSolve: Button
    lateinit var tvSolution: TextView
    lateinit var toolbar: Toolbar
    lateinit var btnBack: ImageButton // Change to ImageButton for back button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algebra)

        // Initialize views
        etEquation = findViewById(R.id.et_equation)
        btnSolve = findViewById(R.id.btn_solve)
        tvSolution = findViewById(R.id.tv_solution)
        toolbar = findViewById(R.id.toolbar)
        btnBack = findViewById(R.id.btn_back)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set click listeners
        btnSolve.setOnClickListener(this)
        btnBack.setOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_solve) {
            val equation = etEquation.text.toString()
            val solution = solveEquation(equation)
            tvSolution.text = solution
        }
    }

    private fun solveEquation(equation: String): String {
        return try {
            val parts = equation.split("=")
            if (parts.size != 2) {
                return "Invalid equation format"
            }
            val left = parts[0].trim()
            val right = parts[1].trim().toDouble()

            val expression = ExpressionBuilder(left).variables("x").build()
            val result = findRoot(expression, right)
            "x = ${String.format("%.6f", result)}"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    private fun findRoot(expression: net.objecthunter.exp4j.Expression, target: Double): Double {
        var lowerBound = -1e6
        var upperBound = 1e6
        var midPoint: Double
        var result: Double

        while (upperBound - lowerBound > 1e-6) {
            midPoint = (lowerBound + upperBound) / 2.0
            result = expression.setVariable("x", midPoint).evaluate()

            if (result < target) {
                lowerBound = midPoint
            } else {
                upperBound = midPoint
            }
        }
        return (lowerBound + upperBound) / 2.0
    }
}
