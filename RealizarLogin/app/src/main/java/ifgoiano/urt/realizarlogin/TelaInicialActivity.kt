package ifgoiano.urt.realizarlogin

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tela_inicial)
        val bundle = intent.extras
        val nomeUsuario = bundle?.getString("nomeUsuario") ?: "Usuário"

        val mensagem = "Bem vindo, $nomeUsuario!"
        val textView: TextView = findViewById(R.id.textView3)

        // Exibindo a mensagem de boas-vindas no TextView
        textView.text = mensagem
    }
}