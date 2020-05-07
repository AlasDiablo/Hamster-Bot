package fr.alasdiablo.hamster.voice

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GardenVoice: ListenerAdapter() {

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        val guild = event.channelJoined!!.guild
        if (event.channelJoined!!.id == "707917342763647006") {
            val roleIntance = guild.getRolesByName("Cendre", true)
            guild.removeRoleFromMember(event.entity, roleIntance[0]).submit()
            val channel = guild.getTextChannelById("707917208742789161")
            channel!!.sendMessage("<@!${event.entity.id}> renait de ses cendres tel un Phoenix !!").submit()
        }
    }
}