package ifgoiano.urt.estadosrecycle

class Estado (val nome: String,
              val img: Int,
              val capital: String,
              val populacao: String,
              val regiao: String){

    companion object {
        fun getEstados(): List<Estado> {
            return listOf(
                Estado("Goiás", R.drawable.estado_09_goias,"Goiânia","7.056.495","Centro-Oeste"),
//                Planeta("Vênus", R.drawable.planeta_02_venus),
//                Planeta("Terra", R.drawable.planeta_03_terra),
//                Planeta("Marte", R.drawable.planeta_04_marte)
                // Planeta("Júpiter", R.drawable.planeta_05_jupiter),
                // Planeta("Saturno", R.drawable.planeta_06_saturno),
                // Planeta("Urano", R.drawable.planeta_07_urano),
                // Planeta("Netuno", R.drawable.planeta_08_neptuno),
                // Planeta("Plutão", R.drawable.planeta_09_plutao)
            )
        }
    }
}