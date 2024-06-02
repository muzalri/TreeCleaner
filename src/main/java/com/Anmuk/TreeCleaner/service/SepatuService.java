
package com.Anmuk.TreeCleaner.service;

import com.Anmuk.TreeCleaner.model.Sepatu;
import com.Anmuk.TreeCleaner.repository.SepatuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SepatuService {
    @Autowired
    private SepatuRepository sepatuRepository;

    public List<Sepatu> findAll() {
        return sepatuRepository.findAll();
    }

    public Optional<Sepatu> findById(Long id) {
        return sepatuRepository.findById(id);
    }

    public Sepatu save(Sepatu sepatu) {
        return sepatuRepository.save(sepatu);
    }

    public void deleteById(Long id) {
        sepatuRepository.deleteById(id);
    }
}
