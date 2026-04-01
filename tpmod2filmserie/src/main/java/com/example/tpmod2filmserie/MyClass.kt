package com.example.tpmod2filmserie
enum class Statut { A_VOIR, DEJA_VU}
fun main() {
    val mapFilmSerie = mutableMapOf<String, Statut>()
    var userChoice :Int = 0
    do {
        println("Choisissez")
        println("1 - AJouter un film déjà vu")
        println("2 -  Ajouter un film à voir")
        println("3 - Voir toute la liste")
        println("4 - Quittez")
        userChoice = readln().toInt();

        when(userChoice){
            1 -> mapFilmSerie[readln()] = Statut.DEJA_VU
            2 -> mapFilmSerie[readln()] = Statut.A_VOIR
            3 -> for(map in mapFilmSerie){
                 println("${map.key} ${if(map.value == Statut.A_VOIR)"est à voir" else "est déjà vu"}")
            }
        }
    }while (userChoice != 4)
}