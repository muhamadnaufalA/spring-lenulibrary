package com.len.lenulibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.len.lenulibrary.model.Buku;
import com.len.lenulibrary.repository.BukuRepo;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai service untuk data buku.
 */
@Service
public class BukuService {

    @Autowired
    private BukuRepo bukuRepo;

    public Buku create(Buku buku) {
        return bukuRepo.save(buku);
    }

    public List<Buku> getAllBuku() {
        return bukuRepo.findAll();
    }

    public Buku getBukuById(Long id) {
        return bukuRepo.findById(id).orElse(null);
    }

    public Buku updateBuku(Long id, Buku bukuDetails) {
        Buku existingBuku = bukuRepo.findById(id).orElse(null);
        if (existingBuku != null) {
            existingBuku.setJudul(bukuDetails.getJudul());
            existingBuku.setKuantitas(bukuDetails.getKuantitas());
            existingBuku.setPenulis(bukuDetails.getPenulis());
            existingBuku.setTempat_Penyimpanan(bukuDetails.getTempat_Penyimpanan());
            return bukuRepo.save(existingBuku);
        }
        return null; 
    }

    public void deteleBuku(Long id) {
        bukuRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return bukuRepo.existsById(id);
    }
    
}
