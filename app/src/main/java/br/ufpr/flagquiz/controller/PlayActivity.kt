package br.ufpr.flagquiz.controller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.ufpr.flagquiz.R
import br.ufpr.flagquiz.model.Flag
import java.util.ArrayList

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_play)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras

        if (bundle != null){

            val name = bundle.getString("name")
            val flags = intent.getSerializableExtra("flags") as List<Flag>

            // Debug passagem de valores no intent
            //println(bundle.getString("name"))
            //println(flags[0].country)
        }

        //finish()
    }


}