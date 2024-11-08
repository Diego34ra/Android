package ifgoiano.urt.prova_notafiscal

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var semestreRecyclerView: RecyclerView
    private lateinit var valorSaqueEditText: EditText
    private lateinit var cpfEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        semestreRecyclerView = findViewById(R.id.anoRecyclerView)
        valorSaqueEditText = findViewById(R.id.valorSaque)
        cpfEditText = findViewById(R.id.cpf)

        val semestre = Semestre.getSemestres()

        semestreRecyclerView.layoutManager = LinearLayoutManager(this)
        semestreRecyclerView.adapter = SemestreAdapter(semestre)

    }
}