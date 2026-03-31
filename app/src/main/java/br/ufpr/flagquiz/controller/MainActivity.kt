package br.ufpr.flagquiz.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufpr.flagquiz.R
import br.ufpr.flagquiz.model.Flag

class MainActivity : AppCompatActivity() {

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

    fun start(view: View){

        val name: String = findViewById<EditText>(R.id.editName).text.toString()

        var allFlagsList: List<Flag> = listOf(
            Flag("angola", R.drawable.ao),
            Flag("argentina", R.drawable.ar),
            Flag("australia", R.drawable.au),
            Flag("brasil", R.drawable.br),
            Flag("canada", R.drawable.ca),
            Flag("china", R.drawable.cn),
            Flag("finlandia", R.drawable.fi),
            Flag("frança", R.drawable.fr),
            Flag("polonia", R.drawable.pl),
            Flag("portugal", R.drawable.pt),
            Flag("paraguai", R.drawable.py),
            Flag("suecia", R.drawable.se),
            Flag("reino unido", R.drawable.sh),
            Flag("uruguai", R.drawable.uy),
            Flag("dinamarca", R.drawable.dk)
        )

        var flagList = allFlagsList.shuffled().take(5)

        val intent = Intent(this, PlayActivity::class.java)

        intent.putExtra("name", name)

        intent.putExtra("flags", ArrayList(flagList))

        startActivity(intent)

    }
}