package fr.alasdiablo.hamster.leveling;

import fr.alasdiablo.hamster.data.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.time.*;
import java.util.*;

public class VoiceLevel extends ListenerAdapter {

    private final Map<Member, LocalDateTime> memberLogin;

    public VoiceLevel() {
        this.memberLogin = new HashMap<>();
    }

    @Override
    public synchronized void onGuildVoiceUpdate(@Nonnull GuildVoiceUpdateEvent event) {
        if (event.getChannelLeft() == null) {
            this.memberLogin.put(event.getEntity(), LocalDateTime.now());
        } else if (event.getChannelLeft().getId().equals("705914824705441834")) {
            this.memberLogin.put(event.getEntity(), LocalDateTime.now());
        }
        if (event.getChannelJoined() == null || event.getChannelJoined().getId().equals("705914824705441834")) {
            try {
                LocalDateTime start = this.memberLogin.get(event.getEntity());
                LocalDateTime end = LocalDateTime.now();
                Duration duration = Duration.between(start, end);
                long sec = duration.getSeconds();
                float xp = (sec / 60f);
                Database.GlobalStats.addVoiceXp(xp, event.getEntity().getIdLong());
                TextChannel channel = event.getEntity().getGuild().getTextChannelById("707974173137567805");
                assert channel != null;
                channel.sendMessage(
                        new EmbedBuilder()
                                .setTitle("**Royal Hamster Club**")
                                .setColor(new Color(255, 114, 247))
                                .setTimestamp(OffsetDateTime.now())
                                .setDescription("Résumé pour **" + ((event.getEntity().getNickname() != null) ? event.getEntity().getNickname() : event.getEntity().getEffectiveName()) + "**")
                                .setFooter("Hamster Bot", "https://cdn.discordapp.com/avatars/707870242159984653/049fe39e8b1550a050293988dd02a958.png")
                                .addField("Durée du vocal", duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase(), false)
                                .addField("Tu as gagné cette quantité d'XP", String.valueOf(xp), false)
                                .build()
                ).submit();
            } catch (Exception ignored) {}
        }
    }
}
