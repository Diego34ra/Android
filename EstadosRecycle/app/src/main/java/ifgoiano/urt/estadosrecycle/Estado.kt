package ifgoiano.urt.estadosrecycle

class Estado (val nome: String,
              val img: Int,
              val capital: String,
              val populacao: String,
              val regiao: String){

    companion object {
        fun getEstados(): List<Estado> {
            return listOf(
                Estado("Acre", R.drawable.estado_01_acre, "Rio Branco", "906.876", "Norte"),
                Estado("Alagoas", R.drawable.estado_02_alagoas, "Maceió", "3.365.351", "Nordeste"),
                Estado("Amapá", R.drawable.estado_03_amapa, "Macapá", "877.613", "Norte"),
                Estado("Amazonas", R.drawable.estado_04_amazonas, "Manaus", "4.393.050", "Norte"),
                Estado("Bahia", R.drawable.estado_05_bahia, "Salvador", "15.013.859", "Nordeste"),
                Estado("Ceará", R.drawable.estado_06_ceara, "Fortaleza", "9.240.580", "Nordeste"),
                Estado("Distrito Federal", R.drawable.estado_07_distrito, "Brasília", "3.094.325", "Centro-Oeste"),
                Estado("Espírito Santo", R.drawable.estado_08_espiritosanto, "Vitória", "4.108.508", "Sudeste"),
                Estado("Goiás", R.drawable.estado_09_goias, "Goiânia", "7.056.495", "Centro-Oeste"),
                Estado("Maranhão", R.drawable.estado_10_maranhao, "São Luís", "7.249.682", "Nordeste"),
                Estado("Mato Grosso", R.drawable.estado_11_matogrosso, "Cuiabá", "3.526.220", "Centro-Oeste"),
                Estado("Mato Grosso do Sul", R.drawable.estado_12_matogrossosul, "Campo Grande", "2.839.188", "Centro-Oeste"),
                Estado("Minas Gerais", R.drawable.estado_13_minasgerais, "Belo Horizonte", "21.411.923", "Sudeste"),
                Estado("Pará", R.drawable.estado_14_para, "Belém", "9.059.692", "Norte"),
                Estado("Paraíba", R.drawable.estado_15_paraiba, "João Pessoa", "4.158.258", "Nordeste"),
                Estado("Paraná", R.drawable.estado_16_parana, "Curitiba", "11.807.819", "Sul"),
                Estado("Pernambuco", R.drawable.estado_17_pernambuco, "Recife", "9.782.845", "Nordeste"),
                Estado("Piauí", R.drawable.estado_18_piaui, "Teresina", "3.327.296", "Nordeste"),
                Estado("Rio de Janeiro", R.drawable.estado_19_riodejaneiro, "Rio de Janeiro", "17.366.189", "Sudeste"),
                Estado("Rio Grande do Norte", R.drawable.estado_20_riograndedonorte, "Natal", "3.560.903", "Nordeste"),
                Estado("Rio Grande do Sul", R.drawable.estado_21_riograndedosul, "Porto Alegre", "11.466.630", "Sul"),
                Estado("Rondônia", R.drawable.estado_22_rondonia, "Porto Velho", "1.861.548", "Norte"),
                Estado("Roraima", R.drawable.estado_23_roraima, "Boa Vista", "652.713", "Norte"),
                Estado("Santa Catarina", R.drawable.estado_24_santacatarina, "Florianópolis", "7.727.743", "Sul"),
                Estado("São Paulo", R.drawable.estado_25_saopaulo, "São Paulo", "46.289.333", "Sudeste"),
                Estado("Sergipe", R.drawable.estado_26_sergipe, "Aracaju", "2.355.491", "Nordeste"),
                Estado("Tocantins", R.drawable.estado_27_tocatins, "Palmas", "1.615.512", "Norte")

            )
        }
    }
}