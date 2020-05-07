package fr.alasdiablo.hamster

import fr.alasdiablo.hamster.command.CommandHandler
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity

class HamsterBot {
    companion object {
        val PREFIX = ">"
    }
}

fun main(vararg args: String) {
    if (args.size == 1) {
        val token = args[0]
        val builder = JDABuilder.createDefault(token).setActivity(Activity.watching("Les Hamster Dissident"))
        builder.addEventListeners(CommandHandler())
        val bot = builder.build()
        bot.awaitReady()
    } else {
        println("Pls add a token in the command line")
    }
}