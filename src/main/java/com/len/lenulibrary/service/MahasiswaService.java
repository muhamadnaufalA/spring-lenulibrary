package com.len.lenulibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.len.lenulibrary.model.Mahasiswa;
import com.len.lenulibrary.repository.MahasiswaRepo;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai service untuk data mahasiswa.
 */
@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    public Mahasiswa create(Mahasiswa mahasiswa) {
        return mahasiswaRepo.save(mahasiswa);
    }

    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepo.findAll();
    }

    public Mahasiswa getMahasiswaById(Long id) {
        return mahasiswaRepo.findById(id).orElse(null);
    }

    public Mahasiswa updateMahasiswa(Long id, Mahasiswa mahasiswaDetails) {
        Mahasiswa existingMahasiswa = mahasiswaRepo.findById(id).orElse(null);
        if (existingMahasiswa != null) {
            existingMahasiswa.setNama(mahasiswaDetails.getNama());
            existingMahasiswa.setNim(mahasiswaDetails.getNim());
            existingMahasiswa.setJurusan(mahasiswaDetails.getJurusan());
            return mahasiswaRepo.save(existingMahasiswa);
        }
        return null; 
    }

    public void deleteMahasiswa(Long id) {
        mahasiswaRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return mahasiswaRepo.existsById(id);
    }
    
}
