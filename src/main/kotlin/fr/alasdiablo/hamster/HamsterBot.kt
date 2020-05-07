package fr.alasdiablo.hamster

import fr.alasdiablo.hamster.command.CommandHandler
import fr.alasdiablo.hamster.event.NewHamster
import fr.alasdiablo.hamster.leveling.MessageLevel
import fr.alasdiablo.hamster.leveling.VoiceLevel
import fr.alasdiablo.hamster.voice.FurnaceVoice
import fr.alasdiablo.hamster.voice.GardenVoice
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity

class HamsterBot {
    companion object {
        const val PREFIX = ">"
    }
}

fun main(vararg args: String) {
    if (args.size == 1) {
        val token = args[0]
        val builder = JDABuilder.createDefault(token).setActivity(Activity.watching("Les Hamster Dissident"))
        val bot = builder.build()
        bot.addEventListener(CommandHandler(bot))
        bot.addEventListener(FurnaceVoice())
        bot.addEventListener(GardenVoice())
        bot.addEventListener(NewHamster())
        bot.addEventListener(VoiceLevel())
        bot.addEventListener(MessageLevel())
        bot.awaitReady()
    } else {
        println("Pls add a token in the command line")
    }
}