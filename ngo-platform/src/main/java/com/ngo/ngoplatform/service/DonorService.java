package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Donor;
import com.ngo.ngoplatform.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id).orElse(null);
    }

    public Donor updateDonor(Long id, Donor donor) {

        Donor existing = donorRepository.findById(id).orElseThrow();

        existing.setName(donor.getName());
        existing.setEmail(donor.getEmail());
        existing.setPassword(donor.getPassword());
        existing.setPhone(donor.getPhone());
        existing.setAddress(donor.getAddress());

        return donorRepository.save(existing);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

}