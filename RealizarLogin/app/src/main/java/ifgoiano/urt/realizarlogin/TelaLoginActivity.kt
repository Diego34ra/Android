package ifgoiano.urt.realizarlogin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaLoginActivity : AppCompatActivity() {
    private val usuarioPadrao = "usuario"
    private val senhaPadrao = "senha123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextEmail: EditText = findViewById(R.id.editTextTextEmailAddress)
        val editTextSenha: EditText = findViewById(R.id.editTextTextPassword)
        val btnEntrar: Button = findViewById(R.id.button)

        btnEntrar.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()

            if (validarLogin(email, senha)) {
                val intent = Intent(this,TelaInicialActivity::class.java)
                val bundle = Bundle()
                bundle.putString("nomeUsuario", email)

                intent.putExtras(bundle)
                startActivity(intent)
            } else {
                mostrarAlerta()
            }
        }
    }

    private fun validarLogin(email: String, senha: String): Boolean {
        return email == usuarioPadrao && senha == senhaPadrao
    }

    private fun mostrarAlerta() {
        AlertDialog.Builder(this)
            .setTitle("Login Inválido")
            .setMessage("O login ou a senha está incorreto.")
            .setPositiveButton("OK", null)
            .show()
    }
}