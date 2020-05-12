package fr.alasdiablo.hamster.service

import net.dv8tion.jda.api.JDA
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class ServiceHandler(val bot: JDA) {

    fun startService() {

    }

    private fun dailyResume() {
        val scheduler = Executors.newScheduledThreadPool(1)
        val midnight = LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES)
        scheduler.scheduleAtFixedRate({

        }, midnight, 1440, TimeUnit.MINUTES)

    }
}