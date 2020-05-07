package fr.alasdiablo.hamster.command

import fr.alasdiablo.hamster.HamsterBot
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandHandler: ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            event.channel.sendMessage("Le bot ne prend pas en charge les message privé !")
        } else {
            val message = event.message.contentRaw
            when {
                message.startsWith(HamsterBot.PREFIX + "help") -> HelpCommand().run(message, event)
            }
        }
    }
}