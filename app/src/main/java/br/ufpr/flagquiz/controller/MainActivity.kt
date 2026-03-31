package br.ufpr.flagquiz.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufpr.flagquiz.R
import br.ufpr.flagquiz.model.Flag

class MainActivity : AppCompatActivity() {
    private var rodadaAtual = 0
    private var acertos = 0
    private lateinit var flagList: List<Flag>
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun start(view: View) {
        name = findViewById<EditText>(R.id.editName).text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show()
            return
        }

        val allFlagsList: List<Flag> = listOf(
            Flag("Angola", R.drawable.ao),
            Flag("Argentina", R.drawable.ar),
            Flag("Australia", R.drawable.au),
            Flag("Brasil", R.drawable.br),
            Flag("Canada", R.drawable.ca),
            Flag("China", R.drawable.cn),
            Flag("Finlandia", R.drawable.fi),
            Flag("India", R.drawable.ind),
            Flag("Polonia", R.drawable.pl),
            Flag("Portugal", R.drawable.pt),
            Flag("Paraguai", R.drawable.py),
            Flag("Suecia", R.drawable.se),
            Flag("Reino Unido", R.drawable.sh),
            Flag("Uruguai", R.drawable.uy),
            Flag("Dinamarca", R.drawable.dk)
        )

        flagList = allFlagsList.shuffled().take(5)
        rodadaAtual = 0
        acertos = 0
        executarRodada()
    }

    private fun executarRodada() {
        if (rodadaAtual < 5) {
            val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("flag", flagList[rodadaAtual])
            intent.putExtra("numeroRodada", rodadaAtual + 1)
            startActivityForResult(intent, 100)
        } else {
            val intentResultado = Intent(this, ScoreActivity::class.java)
            intentResultado.putExtra("nome", name)
            intentResultado.putExtra("pontos", acertos * 20)
            startActivity(intentResultado)
            findViewById<EditText>(R.id.editName).setText("")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val acertou = data?.getBooleanExtra("acertou", false) ?: false
            if (acertou) acertos++
            rodadaAtual++
            executarRodada()
        }
    }
}