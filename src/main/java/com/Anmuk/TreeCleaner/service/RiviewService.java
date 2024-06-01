package com.Anmuk.TreeCleaner.service;

import com.Anmuk.TreeCleaner.model.Riview;
import com.Anmuk.TreeCleaner.repository.RiviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RiviewService {
    @Autowired
    private RiviewRepository riviewRepository;

    public List<Riview> findAll() {
        return riviewRepository.findAll();
    }

    public Riview findById(Long id) {
        return riviewRepository.findById(id).orElse(null);
    }

    public void save(Riview riview) {
        riviewRepository.save(riview);
    }

    public void deleteById(Long id) {
        riviewRepository.deleteById(id);
    }

    public Riview updateRiview(Long id, Riview riviewDetails) {
        Riview riview = riviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Riview not found with id: " + id));
        riview.setNama(riviewDetails.getNama());
        riview.setDeskripsi(riviewDetails.getDeskripsi());
        riview.setGambar(riviewDetails.getGambar());
        return riviewRepository.save(riview);
    }

    public void deleteRiview(Long id) {
        Riview riview = riviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Riview not found with id: " + id));
        riviewRepository.delete(riview);
    }
}
