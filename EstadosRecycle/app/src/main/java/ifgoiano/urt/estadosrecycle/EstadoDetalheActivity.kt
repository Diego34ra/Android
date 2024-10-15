package ifgoiano.urt.estadosrecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EstadoDetalheActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado_detalhe)

        // Recupera os dados passados pela Intent
        val nome = intent.getStringExtra("NOME")
        val img = intent.getIntExtra("IMG", 0)
        val capital = intent.getStringExtra("CAPITAL")
        val populacao = intent.getStringExtra("POPULACAO")
        val regiao = intent.getStringExtra("REGIAO")

        // Atualiza os dados na interface
        findViewById<TextView>(R.id.estadoNome).text = nome
        findViewById<ImageView>(R.id.estadoBandeira).setImageResource(img)
        findViewById<TextView>(R.id.estadoCapital).text = "Capital: $capital"
        findViewById<TextView>(R.id.estadoPopulacao).text = "População: $populacao"
        findViewById<TextView>(R.id.estadoRegiao).text = "Região:  $regiao"
    }
}
