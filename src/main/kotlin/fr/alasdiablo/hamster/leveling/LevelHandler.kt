package fr.alasdiablo.hamster.leveling

import fr.alasdiablo.hamster.data.Database
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent

class LevelHandler {
    fun checkVoiceLevel(userId: Long, event: GuildVoiceUpdateEvent) {
        if (userId != 707870242159984653 &&
            userId != 159985870458322944 &&
            userId != 213466096718708737
        ) {
            val xp = Database.GlobalStats.getVoiceXp(userId)
            val currentLevel = Database.LevelStats.getLevel(userId)
            val channel = event.entity.guild.getTextChannelById("707974173137567805")
            val guild = event.entity.guild
            if (xp >= 1024 && currentLevel < 1) {
                Database.LevelStats.setLevel(1, userId)
                channel!!.sendMessage(
                        ":musical_note: Notre hamster <@$userId> viens de devenir un **Hamster Pipelette** ! :musical_note:"
                ).submit()
                guild.addRoleToMember(userId, guild.getRolesByName("Hamster Pipelette", true)[0])
            }
            if (xp >= 3072 && currentLevel < 2) {
                Database.LevelStats.setLevel(1, userId)
                channel!!.sendMessage(
                        ":musical_note: Notre hamster <@$userId> viens de devenir un **Hamster Causant** ! :musical_note:"
                ).submit()
                guild.removeRoleFromMember(userId, guild.getRolesByName("Hamster Pipelette", true)[0])
                guild.addRoleToMember(userId, guild.getRolesByName("Hamster Causant", true)[0])
            }
            if (xp >= 7168 && currentLevel < 3) {
                Database.LevelStats.setLevel(1, userId)
                channel!!.sendMessage(
                        ":musical_note: Notre hamster <@$userId> viens de devenir un **Hamster Bavard** ! :musical_note:"
                ).submit()
                guild.removeRoleFromMember(userId, guild.getRolesByName("Hamster Causant", true)[0])
                guild.addRoleToMember(userId, guild.getRolesByName("Hamster Bavard", true)[0])
            }
            if (xp >= 15360 && currentLevel < 4) {
                Database.LevelStats.setLevel(1, userId)
                channel!!.sendMessage(
                        ":musical_note: Notre hamster <@$userId> viens de devenir un **Hamster Commère** ! :musical_note:"
                ).submit()
                guild.removeRoleFromMember(userId, guild.getRolesByName("Hamster Bavard", true)[0])
                guild.addRoleToMember(userId, guild.getRolesByName("Hamster Commère", true)[0])
            }
            if (xp >= 31744 && currentLevel < 5) {
                Database.LevelStats.setLevel(1, userId)
                channel!!.sendMessage(
                        ":musical_note: Notre hamster <@$userId> viens de devenir un **Hamster Orateur** ! :musical_note:"
                ).submit()
                guild.removeRoleFromMember(userId, guild.getRolesByName("Hamster Commère", true)[0])
                guild.addRoleToMember(userId, guild.getRolesByName("Hamster Orateur", true)[0])
            }
        }
    }
}