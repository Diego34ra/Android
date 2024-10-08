package ifgoiano.urt.pesquisaeleitoral

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    private lateinit var tvDados: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        tvDados = findViewById(R.id.tv_dados)

        val bundle = intent.extras

        val nomeCompleto = bundle?.getString("nomeCompleto") ?: "N/A"
        val tituloEleitor = bundle?.getString("tituloEleitor") ?: "N/A"
        val zona = bundle?.getString("zona") ?: "N/A"
        val secao = bundle?.getString("secao") ?: "N/A"
        val cidade = bundle?.getString("cidade") ?: "N/A"
        val estado = bundle?.getString("estado") ?: "N/A"
        val vereador = bundle?.getString("vereador") ?: "N/A"
        val prefeito = bundle?.getString("prefeito") ?: "N/A"
        val partido = bundle?.getString("partido") ?: "N/A"

        val dadosFormatados = """
            Nome Completo: $nomeCompleto
            Título de Eleitor: $tituloEleitor
            Zona: $zona
            Seção: $secao
            Cidade: $cidade
            Estado: $estado
            Vereador escolhido: $vereador
            Prefeito escolhido: $prefeito
            Partido preferido: $partido
        """.trimIndent()

        tvDados.text = dadosFormatados
    }
}