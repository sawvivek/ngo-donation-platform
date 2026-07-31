package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Contribution;
import com.ngo.ngoplatform.entity.Need;
import com.ngo.ngoplatform.repository.ContributionRepository;
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

    public Contribution saveContribution(Contribution contribution) {

        contribution.setDonationDate(LocalDate.now());

        Need need = needRepository.findById(
                contribution.getNeed().getId()
        ).orElseThrow();

        if (contribution.getType().equalsIgnoreCase("MONEY")) {

            double amount = contribution.getAmount() == null ? 0 : contribution.getAmount();

            if (need.getCurrentAmount() == null)
                need.setCurrentAmount(0.0);

            need.setCurrentAmount(
                    need.getCurrentAmount() + amount
            );

            if (need.getCurrentAmount() >= need.getTargetAmount()) {
                need.setStatus("COMPLETED");
            }

        } else {

            int qty = contribution.getQuantity() == null ? 0 : contribution.getQuantity();

            if (need.getReceivedQuantity() == null)
                need.setReceivedQuantity(0);

            need.setReceivedQuantity(
                    need.getReceivedQuantity() + qty
            );

            if (need.getReceivedQuantity() >= need.getRequiredQuantity()) {
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

    public void deleteContribution(Long id) {
        contributionRepository.deleteById(id);
    }
}