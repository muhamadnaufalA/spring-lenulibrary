package com.len.lenulibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.len.lenulibrary.service.BukuService;
import com.len.lenulibrary.service.MahasiswaService;
import com.len.lenulibrary.service.PeminjamanService;
import com.len.lenulibrary.model.Buku;
import com.len.lenulibrary.model.Mahasiswa;
import com.len.lenulibrary.model.Peminjaman;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai controller untuk data peminjaman
 */
@RestController
@RequestMapping("/peminjaman")
public class PeminjamanController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private PeminjamanService peminjamanService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createPeminjaman(@RequestBody Map<String, String> request) {
        try{
            Long mahasiswaId = Long.parseLong(request.get("mahasiswa_id_mhs"));
            Long bukuId = Long.parseLong(request.get("buku_id_buku"));
            String tanggalPeminjaman = request.get("tanggalPeminjaman");
            String tanggalBatasPengembalian = request.get("tanggalBatasPengembalian");

            // Get Mahasiswa and Buku from IDs
            Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(mahasiswaId);
            Buku buku = bukuService.getBukuById(bukuId);

            // Return status not found jika data mahasiswa tidak ada
            if (mahasiswa == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Mahasiswa dengan ID " + mahasiswaId + " tidak ditemukan.");
            }
            // Return status not found jika data buku tidak ada
            if (buku == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Buku dengan ID " + bukuId + " tidak ditemukan.");
            }

            // Create Peminjaman object
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setMahasiswa(mahasiswa);
            peminjaman.setBuku(buku);
            peminjaman.setTanggalPeminjaman(LocalDate.parse(tanggalPeminjaman));
            peminjaman.setTanggalBatasPengembalian(LocalDate.parse(tanggalBatasPengembalian));

            Peminjaman createdPeminjaman = peminjamanService.createPeminjaman(peminjaman);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPeminjaman);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }

    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Peminjaman> getPeminjamanById(@PathVariable Long id) {
        Peminjaman peminjaman = peminjamanService.getPeminjamanById(id);
        if (peminjaman != null){
            return new ResponseEntity<>(peminjaman, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    //READ LIST PEMINJAMAN
    @GetMapping
    public ResponseEntity<List<Peminjaman>> getAllPeminjaman() {
        List<Peminjaman> listPeminjaman = peminjamanService.getAllPeminjaman();
        return new ResponseEntity<>(listPeminjaman, HttpStatus.OK);
    }

    // UPDATE DATA PEMINJAMAN
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePeminjaman(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try{
            Long mahasiswaId = Long.parseLong(request.get("mahasiswa_id_mhs"));
            Long bukuId = Long.parseLong(request.get("buku_id_buku"));
            String tanggalPeminjaman = request.get("tanggalPeminjaman");
            String tanggalBatasPengembalian = request.get("tanggalBatasPengembalian");
            String tanggalPengembalian = request.get("tanggalPengembalian");

            // Get Mahasiswa and Buku from ID
            Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(mahasiswaId);
            Buku buku = bukuService.getBukuById(bukuId);

            // Return status not found jika data mahasiswa tidak ada
            if (mahasiswa == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Mahasiswa dengan ID " + mahasiswaId + " tidak ditemukan.");
            }
            // Return status not found jika data buku tidak ada
            if (buku == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Buku dengan ID " + bukuId + " tidak ditemukan.");
            }

            // Get Peminjaman to update dan return status not found jika data peminjaman tidak ada
            Peminjaman peminjaman = peminjamanService.getPeminjamanById(id);
            if (peminjaman == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Peminjaman dengan ID " + id + " tidak ditemukan.");
            }

            // Update Peminjaman object
            peminjaman.setMahasiswa(mahasiswa);
            peminjaman.setBuku(buku);
            peminjaman.setTanggalPeminjaman(LocalDate.parse(tanggalPeminjaman));
            peminjaman.setTanggalBatasPengembalian(LocalDate.parse(tanggalBatasPengembalian));
            if(tanggalPengembalian == null){
                peminjaman.setTanggalPengembalian(null);
            }else{
                peminjaman.setTanggalPengembalian(LocalDate.parse(tanggalPengembalian));
            }
            

            Peminjaman updatedPeminjaman = peminjamanService.updatePeminjaman(id, peminjaman);
            if (updatedPeminjaman != null) {
                return ResponseEntity.status(HttpStatus.OK)
                                     .body("Peminjaman dengan ID " + id + " berhasil diperbarui. Denda: " + updatedPeminjaman.getDenda());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body("Gagal memperbarui peminjaman dengan ID " + id);
            }
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }

    //DETELE DATA PEMINJAMAN
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePeminjaman(@PathVariable Long id) {
       if (peminjamanService.existsById(id)) {
            peminjamanService.deletePeminjaman(id);
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed: Data Peminjaman not found", HttpStatus.NOT_FOUND);
        }
    }

}
