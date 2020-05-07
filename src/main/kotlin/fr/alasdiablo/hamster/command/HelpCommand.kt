package fr.alasdiablo.hamster.command

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color
import java.time.OffsetDateTime

class HelpCommand: ICommand {
    override fun run(message: String, event: MessageReceivedEvent) {
        event.channel.sendMessage(
            EmbedBuilder()
                .setTitle("**Hamster Bot Help**")
                .setColor(Color(4641526))
                .setTimestamp(OffsetDateTime.now())
                .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                .addField(">help", "Affiche l'aide", true)
                .addField("Test", "<:sure:705801080566579270>", true)
                .build()
        ).submit()
    }
}