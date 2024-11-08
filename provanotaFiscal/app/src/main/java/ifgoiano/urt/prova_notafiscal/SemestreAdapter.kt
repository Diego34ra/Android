package ifgoiano.urt.prova_notafiscal

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SemestreAdapter(private val semestres: List<Semestre>) :
    RecyclerView.Adapter<SemestreAdapter.SemestreViewHolder>() {

    class SemestreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val semestre: TextView = view.findViewById(R.id.semestre)
        val ano: TextView = view.findViewById(R.id.ano)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemestreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_ano_item,
            parent, false)
        return SemestreViewHolder(view)
    }

    override fun onBindViewHolder(holder: SemestreViewHolder, position: Int) {
        val ano = semestres[position]
        holder.ano.text = ano.ano.toString()
        holder.semestre.text = ano.semestre
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SolicitarSaqueActivity::class.java)
            val cpf = (context as MainActivity).findViewById<EditText>(R.id.cpf).text.toString()
            val saque = (context as MainActivity).findViewById<EditText>(R.id.valorSaque).text.toString()
            Log.d("teste", "saque $saque")
            intent.putExtra("ano", ano.ano)
            intent.putExtra("cpf", cpf)
            intent.putExtra("valorSaque", saque)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return semestres.size
    }
}