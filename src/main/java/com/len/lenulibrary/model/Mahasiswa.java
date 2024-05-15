package com.len.lenulibrary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mahasiswa")
/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai model untuk data Mahasiswa
 */
public class Mahasiswa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String nim;
    private String jurusan;

    //Default constructor
    public Mahasiswa(){

    }

    // Contructor Berparameter
    public Mahasiswa(Long id, String nama, String nim, String jurusan){
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getJurusan() {
        return jurusan;
    }
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    
}

