package ifgoiano.urt.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var etNomeCompleto: EditText
    private lateinit var etTituloEleitor: EditText
    private lateinit var etZona: EditText
    private lateinit var etSecao: EditText
    private lateinit var etCidade: EditText
    private lateinit var spinnerEstado: Spinner
    private lateinit var spinnerVereador: Spinner
    private lateinit var spinnerPrefeito: Spinner
    private lateinit var checkBoxPartidos: List<CheckBox>
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNomeCompleto = findViewById(R.id.et_nome_completo)
        etTituloEleitor = findViewById(R.id.et_titulo_eleitor)
        etZona = findViewById(R.id.et_zona)
        etSecao = findViewById(R.id.et_secao)
        etCidade = findViewById(R.id.et_cidade)
        spinnerEstado = findViewById(R.id.spinner_estado)
        spinnerVereador = findViewById(R.id.spinner_vereador)
        spinnerPrefeito = findViewById(R.id.spinner_prefeito)
        btnEnviar = findViewById(R.id.btn_enviar)

        val estadosAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.estados_brasil,
            android.R.layout.simple_spinner_item
        )
        estadosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstado.adapter = estadosAdapter

        val vereadoresAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.vereadores,
            android.R.layout.simple_spinner_item
        )
        vereadoresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVereador.adapter = vereadoresAdapter

        // Configurando o Spinner de prefeitos
        val prefeitosAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.prefeitos,
            android.R.layout.simple_spinner_item
        )
        prefeitosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrefeito.adapter = prefeitosAdapter

        checkBoxPartidos = listOf(
            findViewById(R.id.checkbox_partido_a),
            findViewById(R.id.checkbox_partido_b),
            findViewById(R.id.checkbox_partido_c),
            findViewById(R.id.checkbox_partido_d),
            findViewById(R.id.checkbox_partido_e),
            findViewById(R.id.checkbox_partido_f)
        )

        btnEnviar.setOnClickListener {
            val nomeCompleto = etNomeCompleto.text.toString()
            val tituloEleitor = etTituloEleitor.text.toString()
            val zona = etZona.text.toString()
            val secao = etSecao.text.toString()
            val cidade = etCidade.text.toString()
            val estado = spinnerEstado.selectedItem.toString()
            val vereador = spinnerVereador.selectedItem.toString()
            val prefeito = spinnerPrefeito.selectedItem.toString()

            val partidosSelecionados = mutableListOf<String>()
            checkBoxPartidos.forEach { checkBox ->
                if (checkBox.isChecked) {
                    partidosSelecionados.add(checkBox.text.toString())
                }
            }

            val partidos = partidosSelecionados.joinToString(", ")

            val intent = Intent(this, ResultadoActivity::class.java)
            val bundle = Bundle()
            bundle.putString("nomeCompleto", nomeCompleto)
            bundle.putString("tituloEleitor", tituloEleitor)
            bundle.putString("zona", zona)
            bundle.putString("secao", secao)
            bundle.putString("cidade", cidade)
            bundle.putString("estado", estado)
            bundle.putString("vereador", vereador)
            bundle.putString("prefeito", prefeito)
            bundle.putString("vereador", vereador)
            bundle.putString("partido", partidos)

            intent.putExtras(bundle)
            startActivity(intent)
        }

    }
}