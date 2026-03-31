package br.ufpr.flagquiz.controller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufpr.flagquiz.R

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = intent.getStringExtra("nome")
        val pontos = intent.getIntExtra("pontos", 0)

        findViewById<TextView>(R.id.textViewNameScore).text = nome
        findViewById<TextView>(R.id.textViewScore).text = "$pontos PONTOS"

        findViewById<Button>(R.id.buttonReplay).setOnClickListener {
            finish()
        }
    }
}