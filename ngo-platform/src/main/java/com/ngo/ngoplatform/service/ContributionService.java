package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Contribution;
import com.ngo.ngoplatform.entity.Donor;
import com.ngo.ngoplatform.entity.Need;
import com.ngo.ngoplatform.repository.ContributionRepository;
import com.ngo.ngoplatform.repository.DonorRepository;
import com.ngo.ngoplatform.repository.NeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private NeedRepository needRepository;

    @Autowired
    private DonorRepository donorRepository;

    public Contribution saveContribution(Contribution contribution) {

        if (contribution.getType() == null) {
            throw new IllegalArgumentException("Contribution type is required (MONEY or ITEM)");
        }

        if (contribution.getNeed() == null || contribution.getNeed().getId() == null) {
            throw new IllegalArgumentException("Need id is required");
        }

        contribution.setDonationDate(LocalDate.now());

        // fetch the real Need instead of trusting whatever was passed in the request body
        Need need = needRepository.findById(
                contribution.getNeed().getId()
        ).orElseThrow(() -> new RuntimeException("Need not found with id: " + contribution.getNeed().getId()));

        contribution.setNeed(need);

        // fetch the real Donor too (was missing before - request body donor was used as-is)
        if (contribution.getDonor() != null && contribution.getDonor().getId() != null) {
            Donor donor = donorRepository.findById(
                    contribution.getDonor().getId()
            ).orElseThrow(() -> new RuntimeException("Donor not found with id: " + contribution.getDonor().getId()));
            contribution.setDonor(donor);
        }

        if (contribution.getType().equalsIgnoreCase("MONEY")) {

            double amount = contribution.getAmount() == null ? 0 : contribution.getAmount();

            if (need.getCurrentAmount() == null)
                need.setCurrentAmount(0.0);

            need.setCurrentAmount(
                    need.getCurrentAmount() + amount
            );

            double target = need.getTargetAmount() == null ? 0 : need.getTargetAmount();

            if (target > 0 && need.getCurrentAmount() >= target) {
                need.setStatus("COMPLETED");
            }

        } else {

            int qty = contribution.getQuantity() == null ? 0 : contribution.getQuantity();

            if (need.getReceivedQuantity() == null)
                need.setReceivedQuantity(0);

            need.setReceivedQuantity(
                    need.getReceivedQuantity() + qty
            );

            int required = need.getRequiredQuantity() == null ? 0 : need.getRequiredQuantity();

            if (required > 0 && need.getReceivedQuantity() >= required) {
                need.setStatus("COMPLETED");
            }
        }

        needRepository.save(need);

        return contributionRepository.save(contribution);
    }

    public List<Contribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public Contribution getContributionById(Long id) {
        return contributionRepository.findById(id).orElse(null);
    }

    // donor history - Step 2
    public List<Contribution> getContributionsByDonorId(Long donorId) {
        return contributionRepository.findByDonorId(donorId);
    }

    public void deleteContribution(Long id) {
        contributionRepository.deleteById(id);
    }
}