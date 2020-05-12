package fr.alasdiablo.hamster.command

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color
import java.time.OffsetDateTime

class HelpCommand : ICommand {
    override fun run(message: String, event: MessageReceivedEvent) {
        if (event.channel.id != "707917208742789161") {
            event.author.openPrivateChannel().queue { channel ->
                channel.sendMessage("Merci d'utilisé le channel #hamster-bot pour utilisé le bot").submit()
            }
            event.message.delete().complete()
            return
        }
        event.channel.sendMessage(
            EmbedBuilder()
                .setTitle("**Royal Hamster Club**")
                .setColor(Color(255, 114, 247))
                .setTimestamp(OffsetDateTime.now())
                .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                .addField(">help", "Affiche l'aide", true)
                .addField(">xp", "Affiche le Résumé de l'xp optenue", true)
                .addField(">rank <voice/message>", "Affiche classement des hamster", true)
                .addField("**A propos**", "Bot crée par AlasDiablo pour le `Royal Hamster Club`.", false)
                .build()
        ).submit()
        event.message.addReaction("✅").submit()
    }
}