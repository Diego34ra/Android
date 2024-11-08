package ifgoiano.urt.prova_notafiscal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetalhesSaque : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes_saque)

        val cpf = intent.getStringExtra("cpf")
        val banco = intent.getStringExtra("banco")
        val tipoConta = intent.getStringExtra("tipoConta")
        val agencia = intent.getStringExtra("agencia")
        val conta = intent.getStringExtra("conta")
        val valor = intent.getStringExtra("valorSolicitar")

        val dados = """
            CPF: $cpf
            Banco: $banco
            Tipo de Conta: $tipoConta
            Agência: $agencia
            Conta: $conta
            Valor: $valor
        """.trimIndent()

        // Exibindo os dados no TextView
        val displayDataTextView: TextView = findViewById(R.id.detalhesSaque)
        displayDataTextView.text = dados
    }
}