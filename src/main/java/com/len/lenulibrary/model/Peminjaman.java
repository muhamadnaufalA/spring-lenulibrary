package com.len.lenulibrary.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "peminjaman")
/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai model untuk data Peminjaman
 */
public class Peminjaman {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne //Relasi ManyToOne dengan data mahasiswa
    @JoinColumn(name = "mahasiswa_id_mhs")
    private Mahasiswa mahasiswa;

    @ManyToOne //Relasi ManyToOne dengan data buku
    @JoinColumn(name = "buku_id_buku")
    private Buku buku;
    
    private LocalDate tanggalPeminjaman;
    private LocalDate tanggalBatasPengembalian;
    private LocalDate tanggalPengembalian;

    private Long denda; // Atribut denda untuk menghitung denda yang dikenai berdasarkan tanggalBatasPengembalian
                        // dan tanggalPengembalian

    // Default Constructor
    public Peminjaman() { 
    }

    // Contructor Berparameter
    public Peminjaman(Long id, Mahasiswa mahasiswa, Buku buku, LocalDate tanggalPeminjaman, 
                        LocalDate tanggalBatasPengembalian, LocalDate tanggalPengembalian,
                        Long denda) {
        this.id = id;
        this.mahasiswa = mahasiswa;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalBatasPengembalian = tanggalBatasPengembalian;
        this.tanggalPengembalian = tanggalPengembalian;
        this.denda = denda;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }
    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
    public Buku getBuku() {
        return buku;
    }
    public void setBuku(Buku buku) {
        this.buku = buku;
    }
    public LocalDate getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }
    public void setTanggalPeminjaman(LocalDate tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }
    public LocalDate getTanggalBatasPengembalian() {
        return tanggalBatasPengembalian;
    }
    public void setTanggalBatasPengembalian(LocalDate tanggalBatasPengembalian) {
        this.tanggalBatasPengembalian = tanggalBatasPengembalian;
    }
    public LocalDate getTanggalPengembalian() {
        return tanggalPengembalian;
    }
    public void setTanggalPengembalian(LocalDate tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Long getDenda() {
        return denda;
    }
    public void setDenda(Long denda) {
        this.denda = denda;
    }
    
}

