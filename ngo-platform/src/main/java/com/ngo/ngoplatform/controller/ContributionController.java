package com.ngo.ngoplatform.controller;

import com.ngo.ngoplatform.entity.Contribution;
import com.ngo.ngoplatform.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    @Autowired
    private ContributionService contributionService;

    @PostMapping
    public Contribution createContribution(@RequestBody Contribution contribution) {
        return contributionService.saveContribution(contribution);
    }

    @GetMapping
    public List<Contribution> getAllContributions() {
        return contributionService.getAllContributions();
    }

    @GetMapping("/{id}")
    public Contribution getContributionById(@PathVariable Long id) {
        return contributionService.getContributionById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteContribution(@PathVariable Long id) {
        contributionService.deleteContribution(id);
        return "Contribution Deleted Successfully";
    }
}