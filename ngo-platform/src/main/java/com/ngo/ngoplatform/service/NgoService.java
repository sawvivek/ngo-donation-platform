package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Ngo;
import com.ngo.ngoplatform.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NgoService {

    @Autowired
    private NgoRepository ngoRepository;

    public Ngo saveNgo(Ngo ngo) {
        return ngoRepository.save(ngo);
    }

    public List<Ngo> getAllNgos() {
        return ngoRepository.findAll();
    }

    public Optional<Ngo> getNgoById(Long id) {
        return ngoRepository.findById(id);
    }

    public Ngo updateNgo(Long id, Ngo ngo) {

        Ngo existing = ngoRepository.findById(id).orElseThrow();

        existing.setName(ngo.getName());
        existing.setEmail(ngo.getEmail());
        existing.setPassword(ngo.getPassword());
        existing.setPhone(ngo.getPhone());
        existing.setAddress(ngo.getAddress());
        existing.setDescription(ngo.getDescription());

        return ngoRepository.save(existing);
    }

    public void deleteNgo(Long id) {
        ngoRepository.deleteById(id);
    }

}