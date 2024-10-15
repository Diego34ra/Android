package ifgoiano.urt.planetas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val imagem: ImageView by lazy { findViewById(R.id.imageView) }
    private val combo: Spinner by lazy { findViewById(R.id.spinner) }
    val planetas = Planeta()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        combo.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, planetas.getPlanetas()
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        // Se selecionar algum planeta, atualiza a imagem
        combo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, posicao: Int, id: Long) {
                // Atualiza a imagem
                planetas.getImagemPlaneta(posicao)?.let { imagem.setImageResource(it) }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { TODO("Not yet implemented" )
            }
        }
    }
}