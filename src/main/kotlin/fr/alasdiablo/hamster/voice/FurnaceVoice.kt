package fr.alasdiablo.hamster.voice

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class FurnaceVoice : ListenerAdapter() {

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        val guild = event.channelJoined!!.guild
        if (event.channelJoined!!.id == "707706217614671922") {
            val roleIntance = guild.getRolesByName("Cendre", true)
            guild.addRoleToMember(event.entity, roleIntance[0]).submit()
            val channel = guild.getTextChannelById("707917208742789161")
            channel!!.sendMessage("<@!${event.entity.id}> a été incinéré !").submit()
        }
    }
}