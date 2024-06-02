
package com.Anmuk.TreeCleaner.service;

import com.Anmuk.TreeCleaner.model.Jasa;
import com.Anmuk.TreeCleaner.repository.JasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JasaService {
    @Autowired
    private JasaRepository jasaRepository;

    public List<Jasa> findAll() {
        return jasaRepository.findAll();
    }

    public Optional<Jasa> findById(Long id) {
        return jasaRepository.findById(id);
    }

    public Jasa save(Jasa jasa) {
        return jasaRepository.save(jasa);
    }

    public void deleteById(Long id) {
        jasaRepository.deleteById(id);
    }
}
