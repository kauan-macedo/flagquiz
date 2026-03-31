package br.ufpr.flagquiz.controller

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.ufpr.flagquiz.R
import br.ufpr.flagquiz.model.Flag

class PlayActivity : AppCompatActivity() {

    private lateinit var currentFlag: Flag
    private var isCorrect = false
    private var alreadyChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_play)

        val bundle = intent.extras
        if (bundle != null) {
            val numRodada = bundle.getInt("numeroRodada")
            
            currentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("flag", Flag::class.java)!!
            } else {
                intent.getSerializableExtra("flag") as Flag
            }

            findViewById<TextView>(R.id.textViewCount).text = "$numRodada de 5"
            findViewById<ImageView>(R.id.imageFlag).setImageResource(currentFlag.imageResId)
        }
    }

    fun checkAnswer(view: View) {
        val btn = view as Button
        if (alreadyChecked) {
            val intentDeVolta = Intent()
            intentDeVolta.putExtra("acertou", isCorrect)
            setResult(RESULT_OK, intentDeVolta)
            finish()
            return
        }

        val edit = findViewById<EditText>(R.id.editTextFlag)
        val tvStatus = findViewById<TextView>(R.id.textViewShowCorrect)
        val tvLabel = findViewById<TextView>(R.id.textView7)
        val tvCorrect = findViewById<TextView>(R.id.textViewCorrectAnswer)

        val resp = edit.text.toString().trim()
        if (resp.isEmpty()) return

        alreadyChecked = true
        edit.isEnabled = false
        tvStatus.visibility = View.VISIBLE

        if (resp.equals(currentFlag.name, ignoreCase = true)) {
            isCorrect = true
            tvStatus.text = "CORRETO!"
            tvStatus.setTextColor(Color.GREEN)
        } else {
            isCorrect = false
            tvStatus.text = "INCORRETO!"
            tvStatus.setTextColor(Color.RED)
            tvLabel.visibility = View.VISIBLE
            tvCorrect.visibility = View.VISIBLE
            tvCorrect.text = currentFlag.name.uppercase()
        }

        btn.text = "PRÓXIMA"
    }
}