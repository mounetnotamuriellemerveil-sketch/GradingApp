package com.example.gradingapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Student(
    val name: String,
    val score: Int?
)

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val scoreInput = findViewById<EditText>(R.id.scoreInput)
        val calcButton = findViewById<Button>(R.id.calcButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calcButton.setOnClickListener {

            val name = nameInput.text.toString().ifEmpty { "Unknown" }

            val score = scoreInput.text.toString().toIntOrNull()

            val student = Student(name, score)

            if (student.score == null || student.score !in 0..100) {
                resultText.text = "Please enter a valid score (0-100)"
            } else {
                val grade = when (student.score) {
                    in 90..100 -> "A"
                    in 80..89 -> "B"
                    in 70..79 -> "C"
                    in 60..69 -> "D"
                    else -> "F"
                }

                resultText.text =
                    "Name: ${student.name}\nScore: ${student.score}\nGrade: $grade"
            }
        }
    }
}