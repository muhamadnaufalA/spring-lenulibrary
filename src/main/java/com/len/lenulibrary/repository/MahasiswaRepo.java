package com.len.lenulibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.len.lenulibrary.model.Mahasiswa;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai repository JPA untuk data Mahasiswa
 */
@Repository
public interface MahasiswaRepo extends JpaRepository<Mahasiswa, Long>{

    
}
