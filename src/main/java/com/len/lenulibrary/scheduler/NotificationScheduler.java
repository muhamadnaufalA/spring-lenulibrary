package com.len.lenulibrary.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.len.lenulibrary.service.NotificationService;;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi scheduler notifikasi data pengembalian buku yang sudah melwati batas pengembalian
 */
@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * *") // Menjalankan setiap hari jam 00:00:00
    public void sendOverdueNotifications() {
        notificationService.sendOverdueNotifications();
    }
}
