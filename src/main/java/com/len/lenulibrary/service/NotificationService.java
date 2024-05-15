package com.len.lenulibrary.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.len.lenulibrary.model.Peminjaman;
import com.len.lenulibrary.repository.PeminjamanRepo;;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai service untuk notifikasi.
 */
@Service
public class NotificationService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private PeminjamanRepo peminjamanRepository;

    @Scheduled(cron = "0 0 0 * * *") // Menjalankan setiap hari jam 00:00:00
    public void sendOverdueNotifications() {
        LocalDate today = LocalDate.now();

        // Dapatkan daftar peminjaman yang terlambat
        List<Peminjaman> overduePeminjamans = peminjamanRepository.findByTanggalPengembalianIsNullAndTanggalBatasPengembalianBefore(today);

        for (Peminjaman peminjaman : overduePeminjamans) {
            // Kirim notifikasi ke topik /topic/overdue
            messagingTemplate.convertAndSend("/topic/overdue", "Peminjaman terlambat: ID " + peminjaman.getId());
        }
    }
}
