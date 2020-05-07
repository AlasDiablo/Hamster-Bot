package fr.alasdiablo.hamster.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class RoleCommand(val bot: JDA) : ICommand {

    override fun run(message: String, event: MessageReceivedEvent) {
//        if (event.channel.id != "707917208742789161") {
//            event.author.openPrivateChannel().queue { channel ->
//                channel.sendMessage("Merci d'utilisé le channel #hamster-bot pour utilisé le bot").submit()
//            }
//            event.message.delete().complete()
//            return
//        }
//        val command = message.substring((HamsterBot.PREFIX + "role").length - 1)
//        val user = command.substring(command.indexOf("<@!") + 3, command.indexOf(">"))
//        val role = command.substring(command.indexOf("\"") + 1, command.length - 1)
//        val roleIntance = event.guild.getRolesByName(role, true)
//        event.guild.addRoleToMember(user, roleIntance[0]).submit()
//        event.message.addReaction("✅").submit()
    }

}