package ifgoiano.urt.estadosrecycle

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstadoAdapter(private val estados: List<Estado>) : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>() {

    class EstadoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val estadoImage: ImageView = view.findViewById(R.id.estadoImage)
        val estadoName: TextView = view.findViewById(R.id.estadoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_estado_item, parent, false)
        return EstadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val estado = estados[position]
        holder.estadoName.text = estado.nome
        holder.estadoImage.setImageResource(estado.img)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EstadoDetalheActivity::class.java)

            intent.putExtra("NOME", estado.nome)
            intent.putExtra("IMG", estado.img)
            intent.putExtra("CAPITAL", estado.capital)
            intent.putExtra("POPULACAO", estado.populacao)
            intent.putExtra("REGIAO", estado.regiao)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return estados.size
    }
}