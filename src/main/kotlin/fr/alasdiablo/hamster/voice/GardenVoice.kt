package fr.alasdiablo.hamster.voice

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.lang.Exception

class GardenVoice: ListenerAdapter() {

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        try {
            val guild = event.channelJoined!!.guild
            if (event.channelJoined!!.id == "707917342763647006") {
                val roleInstance = guild.getRolesByName("Cendre", true)
                guild.removeRoleFromMember(event.entity, roleInstance[0]).submit()
                val channel = guild.getTextChannelById("707917208742789161")
                channel!!.sendMessage("<@!${event.entity.id}> renait de ses cendres tel un Phoenix !!").submit()
            }
        } catch (e: Exception) {}

    }
}