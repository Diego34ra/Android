package com.ifgoiano.cervejaria

class ExpertCerveja {

    private val cervejasPorTipo = mapOf(
        "Lager" to listOf("Heineken", "Budweiser", "Amstel", "Sol"),
        "Pilsen" to listOf("Brahma", "Antarctica", "Bohemia"),
        "Bock" to listOf("Spaten Optimator", "Samichlaus", "Weihenstephaner Korbinian")
    )

    fun obterCervejasPorTipo(tipo: String): List<String>? {
        return cervejasPorTipo[tipo]
    }

    fun obterTiposDeCerveja(): List<String> {
        return cervejasPorTipo.keys.toList()
    }
}