package ifgoiano.urt.chamaraplicativo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mensagem: EditText = findViewById(R.id.editTextText)
        val remetente: EditText = findViewById(R.id.idRemetente)
        val botaoEnviar: Button = findViewById(R.id.button)
        botaoEnviar.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_SEND).apply {
                data = Uri.parse("smsto:$remetente")
                putExtra("sms_body", mensagem.text.toString())
            }

            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                data = Uri.parse("mailto:$remetente")
                putExtra(
                    Intent.EXTRA_EMAIL,
                    arrayOf("exemplo@email.com")
                )
                putExtra(Intent.EXTRA_SUBJECT, "Assunto do E-mail")
                putExtra(Intent.EXTRA_TEXT, mensagem.text.toString())
            }

            val chooserIntent = Intent.createChooser(smsIntent, "Escolha como enviar").apply {
                putExtra(
                    Intent.EXTRA_INITIAL_INTENTS,
                    arrayOf(emailIntent)
                )
            }

            if (chooserIntent.resolveActivity(packageManager) != null) {
                startActivity(chooserIntent)
            }
        }
    }
}