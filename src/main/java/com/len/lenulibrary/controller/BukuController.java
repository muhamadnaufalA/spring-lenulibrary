package com.len.lenulibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.len.lenulibrary.service.BukuService;
import com.len.lenulibrary.model.Buku;

import java.util.List;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Class ini berfungsi sebagai controller untuk data Buku
 */
@RestController
@RequestMapping("/buku")
public class BukuController {
    
    @Autowired
    private BukuService bukuService;

    // CREATE 
    @PostMapping
    public ResponseEntity<Buku> createBook(@RequestBody Buku buku) {
        Buku cretedBook = bukuService.create(buku);
        return new ResponseEntity<>(cretedBook, HttpStatus.CREATED);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Buku>> getAllBook() {
        List<Buku> listbuku = bukuService.getAllBuku();
        return new ResponseEntity<>(listbuku, HttpStatus.OK);
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Buku> getOnceBook(@PathVariable Long id) {
        Buku buku = bukuService.getBukuById(id);
        if (buku != null) {
            return new ResponseEntity<>(buku, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBookData(@PathVariable Long id, @RequestBody Buku requestBuku) {
        Buku updatedBuku = bukuService.updateBuku(id, requestBuku);
        if (updatedBuku != null) {
            return new ResponseEntity<>(updatedBuku, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deteleBuku(@PathVariable Long id) {
        if (bukuService.existsById(id)) {
            bukuService.deteleBuku(id);
            return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed: Buku not found", HttpStatus.NOT_FOUND);
        }
    }
}
