package com.len.lenulibrary.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.len.lenulibrary.model.Buku;
import com.len.lenulibrary.model.Mahasiswa;
import com.len.lenulibrary.model.Peminjaman;
import com.len.lenulibrary.repository.PeminjamanRepo;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai service untuk data peminjaman.
 */
@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepo peminjamanRepository;

    @Autowired
    private BukuService bukuService;

    // Method untuk create data peminjaman
    public Peminjaman createPeminjaman(Peminjaman peminjaman) {
        Mahasiswa mahasiswa = peminjaman.getMahasiswa();
        LocalDate today = LocalDate.now();

        // Hitung jumlah peminjaman mahasiswa dalam satu bulan
        int countPeminjamanBulanIni = peminjamanRepository.countPeminjamanByMahasiswaAndMonth(mahasiswa, today.getMonthValue(), today.getYear());

        // Batasi jumlah peminjaman maksimal per bulan
        int maxPeminjamanPerBulan = 10;
        if (countPeminjamanBulanIni >= maxPeminjamanPerBulan) {
            throw new RuntimeException("Mahasiswa telah mencapai batas maksimal peminjaman bulanan.");
        }

        // Kurangi quantity buku
        Buku buku = peminjaman.getBuku();
        int currentQuantity = buku.getKuantitas();
        if (currentQuantity > 0) {
            buku.setKuantitas(currentQuantity-1);
            bukuService.updateBuku(buku.getId(), buku);
        } else {
            throw new RuntimeException("Stok buku tidak mencukupi.");
        }

        // Simpan peminjaman
        Peminjaman createdPeminjaman = peminjamanRepository.save(peminjaman);
        
        return peminjamanRepository.save(createdPeminjaman);
    }

    // Method untuk get data peminjaman berdasarkan id
    public Peminjaman getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id).orElse(null);
    }

    // Method untuk get list data peminjaman
    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    // Method untuk update data peminjaman
    public Peminjaman updatePeminjaman(Long id, Peminjaman peminjamanDetails) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElse(null);
        if (peminjaman != null) {
            peminjaman.setMahasiswa(peminjamanDetails.getMahasiswa());
            peminjaman.setBuku(peminjamanDetails.getBuku());
            peminjaman.setTanggalPeminjaman(peminjamanDetails.getTanggalPeminjaman());
            peminjaman.setTanggalBatasPengembalian(peminjamanDetails.getTanggalBatasPengembalian());
            peminjaman.setTanggalPengembalian(peminjamanDetails.getTanggalPengembalian());
            if (peminjamanDetails.getTanggalPengembalian() != null){
                // Tambah quantity buku saat pengembalian
                Buku buku = peminjaman.getBuku();
                int currentQuantity = buku.getKuantitas();
                buku.setKuantitas(currentQuantity+1);
                bukuService.updateBuku(buku.getId(), buku);
            }
            
            // Hitung denda jika terlambat mengembalikan
            LocalDate tanggalPengembalian = peminjamanDetails.getTanggalPengembalian();
            long denda = 0;
            if (tanggalPengembalian != null && tanggalPengembalian.isAfter(peminjaman.getTanggalBatasPengembalian())) {
                long daysLate = peminjaman.getTanggalBatasPengembalian().until(tanggalPengembalian).getDays();
                denda = calculateDenda(daysLate);
                System.out.println("Denda : " + denda);
                peminjaman.setDenda(denda);
            }else{
                peminjaman.setDenda(denda);
            }

            return peminjamanRepository.save(peminjaman);
        }
        return null;
    }

    // Method untuk delete data peminjaman
    public void deletePeminjaman(Long id) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElse(null);
        if (peminjaman != null) {
            if (peminjaman.getTanggalPengembalian() == null){ //cek apakah tanggalPengembalian null atau tidak 
                // Jika null maka Tambah quantity buku
                Buku buku = peminjaman.getBuku();
                int currentQuantity = buku.getKuantitas();
                buku.setKuantitas(currentQuantity+1);
                bukuService.updateBuku(buku.getId(), buku);
            }
            // Hapus peminjaman
            peminjamanRepository.deleteById(id);
        }
    }

    // Method untuk get existing data peminjaman by id (mengembalikan nilai boolean)
    public boolean existsById(Long id) {
        return peminjamanRepository.existsById(id);
    }

    // Method untuk melakukan kalkulasi denda berdasarkan jumlah hari keterlambatan pengembalian buku 
    private long calculateDenda(long daysLate) {
        long denda = 0;
        if (daysLate > 0) {
            long daysRemaining = daysLate;
            while (daysRemaining > 0) {
                if (daysRemaining <= 2) {
                    // Denda untuk 1-2 hari terlambat
                    denda += 1000;
                } else { // Denda untuk lebih dari 2 hari terlambat
                    denda += 1000 * (daysRemaining / 2); // Denda awal untuk 2 hari pertama
                    if (daysRemaining % 2 != 0) {
                        denda += 1000; 
                    }
                }
                daysRemaining -= 1; 
            }
        }
        return denda;
    }
}
