package ifgoiano.urt.planetas

class Planeta {

    private val imagens = intArrayOf(
        R.drawable.planeta_01_mercurio, R.drawable.planeta_02_venus,
        R.drawable.planeta_03_terra, R.drawable.planeta_04_marte
//        ,
//        R.drawable.planeta_05_jupiter, R.drawable.planeta_06_saturno,
//        R.drawable.planeta_07_urano, R.drawable.planeta_08_neptuno,
//        R.drawable.planeta_09_plutao
    )
    private val planetas = arrayOf(
        "Mercúrio", "Vênus", "Terra", "Marte", "Júpiter", "Saturno", "Urano", "Netuno", "Plutão"
    )


    fun getPlanetas(): Array<String>{
        return planetas;
    }

    fun getImagemPlaneta(posicao: Int): Int? {
        return if (posicao in imagens.indices) {
            imagens[posicao]
        } else {
            null
        }
    }

}