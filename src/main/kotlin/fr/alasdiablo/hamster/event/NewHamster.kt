package fr.alasdiablo.hamster.event

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.awt.Color
import java.time.OffsetDateTime

class NewHamster: ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val roleInstance = event.guild.getRolesByName("Hamsters des bois", true)
        event.guild.addRoleToMember(event.member, roleInstance[0]).submit()

        event.user.openPrivateChannel().queue { channel ->
            channel.sendMessage(
                EmbedBuilder()
                    .setTitle("**Royal Hamster Club**")
                    .setColor(Color(255, 114, 247))
                    .setTimestamp(OffsetDateTime.now())
                    .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                    .addField("**Bienvenue**", "Bienvenue sur le Royal Hamster Club !\nAmusez-vous bien et ne soyez pas trop dissidents.", false)
                    .build()
            ).submit()
        }
    }
}