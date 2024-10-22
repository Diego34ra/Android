package ifgoiano.urt.restaurantefirebase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class EditarRestauranteActivity: AppCompatActivity()  {

    private lateinit var etNome: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSalvar: Button
    private var restauranteId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_restaurante)
        // Inicializar as views
        etNome = findViewById(R.id.etNome)
        ratingBar = findViewById(R.id.ratingBar)
        btnSalvar = findViewById(R.id.btnSalvar)
        // Pegar os dados do restaurante da Intent
        restauranteId = intent.getStringExtra("id")
        val nome = intent.getStringExtra("nome")
        val classificacao = intent.getFloatExtra("classificacao", 0.0F)
        // Pré-preencher os dados do restaurante nos campos
        etNome.setText(nome)
        ratingBar.rating = classificacao
        // Supondo que seu layout principal tenha o ID rootLayout
        val rootView = findViewById<LinearLayout>(R.id.linearLayout)

        btnSalvar.setOnClickListener {
            val novoNome = etNome.text.toString()
            val novaClassificacao = ratingBar.rating

            if (restauranteId == null) {
                // Criar um novo restaurante e obter o ID gerado automaticamente
                val restauranteRef =
                    FirebaseFirestore.getInstance().collection("restaurantes").document()
                // Criar um novo objeto Restaurante com os valores atualizados
                val restaurante = Restaurante(
                    id = restauranteRef.id,
                    nome = novoNome,
                    classificacao = novaClassificacao
                )
                // inserir dados
                restauranteRef
                    .set(restaurante)
                    .addOnSuccessListener {
                        //Toast.makeText(this, "Restaurante cadastrado!",
//                        Toast.LENGTH_SHORT).show()
                        Snackbar.make(
                            rootView,
                            "Restaurante cadastrado!", Snackbar.LENGTH_SHORT
                        ).show()
                        // Atrasar o fechamento da Activity
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish() // Fechar a Activity depois que o Snackbar foi exibido
                        }, 2000) // 2000 milissegundos (2 segundos) de atraso
                    }
                    .addOnFailureListener { e ->
                        Log.w("EditarRestaurante", "Erro ao cadastrar documento", e)
                    }
            } else {// Atualizar os dados no Firestore
                // Criar um novo objeto Restaurante com os valores atualizados
                val restaurante = Restaurante(
                    id = restauranteId!!,
                    nome = novoNome,
                    classificacao = novaClassificacao
                )
                FirebaseFirestore.getInstance().collection("restaurantes")
                    .document(restauranteId!!)
                    .set(restaurante)
                    .addOnSuccessListener {
                        //Toast.makeText(this, "Restaurante atualizado!",
//                        Toast.LENGTH_SHORT).show()
                        Snackbar.make(findViewById(android.R.id.content),
                            "Restaurante atualizado!", Snackbar.LENGTH_SHORT).show()
                        // Atrasar o fechamento da Activity
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish() // Fechar a Activity depois que o Snackbar
                        }, 2000) // 2000 milissegundos (2 segundos) de atraso
                    }
                    .addOnFailureListener { e ->
                        Log.w("EditarRestaurante", "Erro ao atualizar documento", e)
                    }
            }
        }
    }
}