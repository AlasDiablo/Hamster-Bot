package fr.alasdiablo.hamster.leveling

import fr.alasdiablo.hamster.data.Database
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageLevel: ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message.contentRaw
        val xp: Float = (message.length * 0.1f)
        Database.addMessageXp(xp, event.author.idLong)
    }
}