package ifgoiano.urt.prova_notafiscal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SolicitarSaqueActivity : AppCompatActivity() {

    private lateinit var cpfEdit: EditText
    private lateinit var valorEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solicitar_saque)

        cpfEdit = findViewById(R.id.cpf_saque)
        valorEdit = findViewById(R.id.valorSolicitar)

        val bundle = intent.extras
        val cpf = bundle?.getString("cpf")
        val saque = bundle?.getString("valorSaque")
        val ano = bundle!!.getInt("ano")

        cpfEdit.setText(cpf)
        valorEdit.setText(saque.toString())

        val cpfEditText: EditText = findViewById(R.id.cpf_saque)
        val bancoSpinner: Spinner = findViewById(R.id.spinner)
        val tipoContaSpinner: Spinner = findViewById(R.id.spinner2)
        val agenciaEditText: EditText = findViewById(R.id.agencia)
        val contaEditText: EditText = findViewById(R.id.conta)
        val valorSolicitarEditText: EditText = findViewById(R.id.valorSolicitar)

        val solicitarSaqueButton: Button = findViewById(R.id.button2)
        solicitarSaqueButton.setOnClickListener {
            val cpf = cpfEditText.text.toString()
            val banco = bancoSpinner.selectedItem.toString()
            val tipoConta = tipoContaSpinner.selectedItem.toString()
            val agencia = agenciaEditText.text.toString()
            val conta = contaEditText.text.toString()
            val saqueInicial = saque?.toFloatOrNull() ?: 0.0f
            val valorFinal = calcularValorSaque(ano, saqueInicial)
            Log.d("testesaque","Valor final do saque: $valorFinal")


            val intent = Intent(this, DetalhesSaque::class.java).apply {
                putExtra("cpf", cpf)
                putExtra("banco", banco)
                putExtra("tipoConta", tipoConta)
                putExtra("agencia", agencia)
                putExtra("conta", conta)
                Log.d("testesaque","Valor final do enviando: $valorFinal")
                putExtra("valorSolicitar", valorFinal)
            }

            startActivity(intent)
        }
    }

    private fun calcularValorSaque(ano: Int, valorSolicitar: Float): Float {
        return when (ano) {
            2020 -> valorSolicitar * 1.0f
            2021 -> valorSolicitar * 0.9f
            2022 -> valorSolicitar * 0.8f
            2023 -> valorSolicitar * 0.7f
            2024 -> valorSolicitar * 0.6f
            else -> valorSolicitar
        }
    }
}
