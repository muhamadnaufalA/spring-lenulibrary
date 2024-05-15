package com.len.lenulibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.len.lenulibrary.service.MahasiswaService;
import com.len.lenulibrary.model.Mahasiswa;

import java.util.List;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai controller untuk data mahasiswa
 */
@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    // CREATE
    @PostMapping
    public ResponseEntity<Mahasiswa> createMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        Mahasiswa createdMahasiswa = mahasiswaService.create(mahasiswa);
        return new ResponseEntity<>(createdMahasiswa, HttpStatus.CREATED);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa() {
        List<Mahasiswa> mahasiswas = mahasiswaService.getAllMahasiswa();
        return new ResponseEntity<>(mahasiswas, HttpStatus.OK);
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Mahasiswa> getMahasiswaById(@PathVariable Long id) {
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(id);
        if (mahasiswa != null) {
            return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Mahasiswa> updateMahasiswa(@PathVariable Long id, @RequestBody Mahasiswa mahasiswaDetails) {
        Mahasiswa updatedMahasiswa = mahasiswaService.updateMahasiswa(id, mahasiswaDetails);
        if (updatedMahasiswa != null) {
            return new ResponseEntity<>(updatedMahasiswa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMahasiswa(@PathVariable Long id) {
        if (mahasiswaService.existsById(id)) {
            mahasiswaService.deleteMahasiswa(id);
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed: Mahasiswa not found", HttpStatus.NOT_FOUND);
        }
    }
}
