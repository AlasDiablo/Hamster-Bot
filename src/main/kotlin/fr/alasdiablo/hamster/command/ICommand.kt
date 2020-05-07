package fr.alasdiablo.hamster.command

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface ICommand {
    fun run(message: String, event: MessageReceivedEvent)
}