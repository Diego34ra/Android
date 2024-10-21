package ifgoiano.urt.paisesfirebase

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCapital: EditText
    private lateinit var editTextPais: EditText
    private lateinit var editTextSigla: EditText
    private lateinit var textViewAviso: TextView
    private lateinit var buttonCriar: Button
    private lateinit var db: FirebaseFirestore
    private var cidade: String = ""
    private var pais: String = ""
    private var sigla: String = ""
    private lateinit var paises: Paises



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = FirebaseFirestore.getInstance()
        editTextCapital = findViewById(R.id.editTextCapital)
        editTextPais = findViewById(R.id.editTextPais)
        editTextSigla = findViewById(R.id.editTextSigla)
        textViewAviso = findViewById(R.id.textViewAviso)
        buttonCriar = findViewById(R.id.buttonCriar)
        buttonCriar.setOnClickListener { criar() }
    }

    private fun criar() {
        val capitais = db.collection("paises")
        cidade = editTextCapital.text.toString()
        pais = editTextPais.text.toString()
        sigla = editTextSigla.text.toString()
        // Verifica se os campos estão vazios e interrompe se necessário
        when {
            TextUtils.isEmpty(cidade) -> {
                editTextCapital.error = "Digite o nome da cidade"
                return
            }
            TextUtils.isEmpty(pais) -> {
                editTextPais.error = "Digite o nome do país"
                return
            }
            TextUtils.isEmpty(sigla) -> {
                editTextSigla.error = "Digite a sigla do país"
                return
            }
        }
        paises = Paises(cidade, pais, sigla)
        // Insere no Firestore e trata o sucesso e a falha
        capitais.document(sigla).set(paises)
            .addOnSuccessListener {
                // Se a inserção for bem-sucedida, limpa os campos e exibe uma mensagem
                editTextCapital.text.clear()
                editTextPais.text.clear()
                editTextSigla.text.clear()
                textViewAviso.text = "Inserido com sucesso!"
            }
            .addOnFailureListener { e ->
                // Se houver falha, exibe uma mensagem de erro no log e na interface
                Log.w("Firestore", "Erro ao inserir documento", e)
                textViewAviso.text = "Erro ao inserir, tente novamente."
            }
    }
}