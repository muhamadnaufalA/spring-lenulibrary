package com.len.lenulibrary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "buku")
/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai model untuk data Buku
 */
public class Buku {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "judul")
    private String judul;

    @Column(name = "penulis")
    private String penulis;

    @Column(name = "kuantitas")
    private int kuantitas;

    @Column(name = "tempat_penyimpanan")
    private String tempat_penyimpanan;

    //Default constructor
    public Buku (){

    }

    // Contructor Berparameter
    public Buku (Long id, String judul, String penulis, int kuantitas, String tempat_penyimpanan){
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.kuantitas = kuantitas;
        this.tempat_penyimpanan = tempat_penyimpanan;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPenulis() {
        return penulis;
    }
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    public int getKuantitas() {
        return kuantitas;
    }
    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }
    public String getTempat_Penyimpanan() {
        return tempat_penyimpanan;
    }
    public void setTempat_Penyimpanan(String tempat_penyimpanan) {
        this.tempat_penyimpanan = tempat_penyimpanan;
    }

}
