package ifgoiano.urt.estadosrecycle

class Estado (val nome: String,
              val img: Int,
              val capital: String,
              val populacao: String,
              val regiao: String){

    companion object {
        fun getEstados(): List<Estado> {
            return listOf(
                Estado("Acre", R.drawable.estado_01_acre,"Rio Branco","830.018","Norte"),
                Estado("Alagoas", R.drawable.estado_02_alagoas,"Maceió","3.127.683","Nordeste"),
                Estado("Amapá", R.drawable.estado_03_amapa,"Macapá","733.759","Norte"),
                Estado("Amazonas", R.drawable.estado_04_amazonas,"Manaus","3.941.613","Norte"),
                Estado("Bahia", R.drawable.estado_05_bahia,"Salvador","14.141.626","Nordeste"),
                Estado("Ceará", R.drawable.estado_06_ceara,"Fortaleza","8.794.957","Nordeste"),
                Estado("Distrito Federal", R.drawable.estado_07_distrito,"Brasília","2.817.381","Centro-Oeste"),
                Estado("Espirito Santo", R.drawable.estado_08_espiritosanto,"Vitória","3.833.712","Sudeste"),
                Estado("Goiás", R.drawable.estado_09_goias,"Goiânia","7.056.495","Centro-Oeste"),
                Estado("Maranhão", R.drawable.estado_10_maranhao,"São Luís","6.776.699","Nordeste"),
                Estado("Mato Grosso", R.drawable.estado_11_matogrosso,"Cuiabá","3.658.649","Centro-Oeste"),
                Estado("Mato Grosso do Sul", R.drawable.estado_12_matogrossosul,"Campo Grande","2.757.013","Centro-Oeste"),
                Estado("Minas Gerais", R.drawable.estado_13_minasgerais,"Belo Horizonte","20.539.989","Sudeste"),
                Estado("Pará", R.drawable.estado_14_para,"Belém","8.120.131","Norte"),
                Estado("Paraíba", R.drawable.estado_15_paraiba,"João Pessoa","3.974.687","Nordeste"),
                Estado("Paraná", R.drawable.estado_16_parana,"Curitiba","11.444.380","Sul"),
                Estado("Pernambuco", R.drawable.estado_17_pernambuco,"Recife","9.058.931","Nordeste"),
                Estado("Piauí", R.drawable.estado_18_piaui,"Teresina","3.271.199","Nordeste"),
                Estado("Rio de Janeiro", R.drawable.estado_19_riodejaneiro,"Rio de Janeiro","16.055.174","Sudeste"),
                Estado("Rio Grande do Norte", R.drawable.estado_20_riograndedonorte,"Natal","3.302.729","Nordeste"),
                Estado("Rio Grande do Sul", R.drawable.estado_21_riograndedosul,"Porto Alegre","10.882.965","Sul"),
                Estado("Rondônia", R.drawable.estado_22_rondonia,"Porto Velho","1.581.196","Norte"),
                Estado("Roraima", R.drawable.estado_23_roraima,"Boa Vista","636.707","Norte"),
                Estado("Santa Catarina", R.drawable.estado_24_santacatarina,"7.610.361","7.727.886","Sul"),
                Estado("São Paulo", R.drawable.estado_25_saopaulo,"São Paulo","44.411.238","Sudeste"),
                Estado("Sergipe", R.drawable.estado_26_sergipe,"Aracaju","2.210.004","Nordeste"),
                Estado("Tocantins", R.drawable.estado_27_tocatins,"Palmas","1.511.460","Norte")

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