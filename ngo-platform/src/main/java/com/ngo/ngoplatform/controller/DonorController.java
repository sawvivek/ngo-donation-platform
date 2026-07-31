package com.ngo.ngoplatform.controller;

import com.ngo.ngoplatform.entity.Contribution;
import com.ngo.ngoplatform.entity.Donor;
import com.ngo.ngoplatform.service.ContributionService;
import com.ngo.ngoplatform.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private ContributionService contributionService;

    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }

    @PutMapping("/{id}")
    public Donor updateDonor(@PathVariable Long id,
                             @RequestBody Donor donor) {
        return donorService.updateDonor(id, donor);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return "Donor Deleted Successfully";
    }

    @GetMapping("/{id}/contributions")
    public List<Contribution> getDonorContributions(@PathVariable Long id) {
        return contributionService.getContributionsByDonorId(id);
    }
}