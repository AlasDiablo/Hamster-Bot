package fr.alasdiablo.hamster.command

import fr.alasdiablo.hamster.data.Database
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color
import java.time.OffsetDateTime

class RankCommand: ICommand {
    override fun run(message: String, event: MessageReceivedEvent) {
        if (event.channel.id != "707917208742789161") {
            event.author.openPrivateChannel().queue { channel ->
                channel.sendMessage("Merci d'utilisé le channel #hamster-bot pour utilisé le bot").submit()
            }
            event.message.delete().complete()
            return
        }
        when {
            message.contains("voice") -> {
                val classementVoice = Database.GlobalStats.getVoiceClassement()
                val sizeClassment = classementVoice.size - 1
                val embed = EmbedBuilder()
                        .setTitle("**Royal Hamster Club**")
                        .setColor(Color(255, 114, 247))
                        .setTimestamp(OffsetDateTime.now())
                        .setDescription("Classement Des Hamster Parleur")
                        .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                var pos = 1
                for (i in 0..sizeClassment) {
                    if (classementVoice[i].key != 707870242159984653 &&
                        classementVoice[i].key != 159985870458322944 &&
                        classementVoice[i].key != 213466096718708737
                    ) {
                        val xp = classementVoice[i].value
                        embed.addField("**- $pos -**", "<@${classementVoice[i].key}> avec $xp XP \n", false)
                        pos++
                    }
                }
                event.channel.sendMessage(
                        embed.build()
                ).submit()
            }
            message.contains("message") -> {
                val classementMessage = Database.GlobalStats.getMessageClassement()
                val sizeClassment = classementMessage.size - 1
                val embed = EmbedBuilder()
                        .setTitle("**Royal Hamster Club**")
                        .setColor(Color(255, 114, 247))
                        .setTimestamp(OffsetDateTime.now())
                        .setDescription("Classement Des Hamster Messagé")
                        .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                var pos = 1
                for (i in 0..sizeClassment) {
                    if (classementMessage[i].key != 707870242159984653 &&
                        classementMessage[i].key != 159985870458322944 &&
                        classementMessage[i].key != 213466096718708737
                    ) {
                        val xp = classementMessage[i].value
                        embed.addField("**- $pos -**", "<@${classementMessage[i].key}> avec $xp XP \n", false)
                        pos++
                    }
                }
                event.channel.sendMessage(
                        embed.build()
                ).submit()
            }
            else -> {
                event.channel.sendMessage("Commande invalide car il manque des argument").submit()
            }
        }
        event.message.addReaction("✅").submit()
    }


    private fun getMemberName(id: Long, event: MessageReceivedEvent): String? {
        var name: String? = null
        event.guild.members.forEach {
            if (it.idLong == id) {
                name = it.effectiveName
            }
        }
        return name
    }

}