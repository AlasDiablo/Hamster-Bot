package fr.alasdiablo.hamster.command

import fr.alasdiablo.hamster.data.Database
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color
import java.time.OffsetDateTime

class XpCommand: ICommand {

    override fun run(message: String, event: MessageReceivedEvent) {
        if (event.channel.id != "707917208742789161") {
            event.author.openPrivateChannel().queue { channel ->
                channel.sendMessage("Merci d'utilisé le channel #hamster-bot pour utilisé le bot").submit()
            }
            event.message.delete().complete()
            return
        }
        val voiceXp = Database.GlobalStats.getVoiceXp(event.author.idLong)
        val messageXp = Database.GlobalStats.getMessageXp(event.author.idLong)
        event.channel.sendMessage(
                EmbedBuilder()
                .setTitle("**Royal Hamster Club**")
                .setColor(Color(255, 114, 247))
                .setTimestamp(OffsetDateTime.now())
                .setDescription("Résumé pour **" + (if (event.member?.nickname != null) event.member!!.nickname else event.member!!.effectiveName) + "**")
                .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                .addField("Xp vocal", voiceXp.toString(), false)
                .addField("Xp textuel", messageXp.toString(), false)
                .build()
        ).submit()
        event.message.addReaction("✅").submit()
    }
}