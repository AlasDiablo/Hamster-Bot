package fr.alasdiablo.hamster

fun main(vararg args: String) {
    if (args.size == 1) {
        val token = args[0]
    } else {
        println("Pls add a token in the command line")
    }
}