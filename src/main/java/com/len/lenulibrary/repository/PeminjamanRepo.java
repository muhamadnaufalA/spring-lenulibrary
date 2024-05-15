package com.len.lenulibrary.repository;

import com.len.lenulibrary.model.Mahasiswa;
import com.len.lenulibrary.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai repository JPA untuk data peminjaman
 */
public interface PeminjamanRepo extends JpaRepository<Peminjaman, Long> {
    /*
     * Method countPeminjamanByMahasiswaAndMonth yang berisikan query custom untuk
     * get jumlah data peminjaman buku seorang mahasiswa dalam 1 bulan
     */
    @Query("SELECT COUNT(p) FROM Peminjaman p " +
        "WHERE p.mahasiswa = :mahasiswa " +
        "AND EXTRACT(MONTH FROM p.tanggalPeminjaman) = :month " +
        "AND EXTRACT(YEAR FROM p.tanggalPeminjaman) = :year")
    int countPeminjamanByMahasiswaAndMonth(@Param("mahasiswa") Mahasiswa mahasiswa,
                                           @Param("month") int month,
                                           @Param("year") int year);
    /*
     * Method findByTanggalPengembalianIsNullAndTanggalBatasPengembalianBefore 
     * berfungsi untuk get tanggal pengembalian buku yang sudah melebihi tanggal batas pengembalian
     * (untuk kebutuhan notifikasi)
     */
    List<Peminjaman> findByTanggalPengembalianIsNullAndTanggalBatasPengembalianBefore(LocalDate date);
}
