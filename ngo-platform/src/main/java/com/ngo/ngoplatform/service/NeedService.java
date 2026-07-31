package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Need;
import com.ngo.ngoplatform.repository.NeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NeedService {

    @Autowired
    private NeedRepository needRepository;

    public Need saveNeed(Need need) {
        return needRepository.save(need);
    }

    public List<Need> getAllNeeds() {
        return needRepository.findAll();
    }

    public Need getNeedById(Long id) {
        return needRepository.findById(id).orElse(null);
    }

    public Need updateNeed(Long id, Need need) {

        Need existing = needRepository.findById(id).orElseThrow();

        existing.setTitle(need.getTitle());
        existing.setDescription(need.getDescription());
        existing.setType(need.getType());
        existing.setTargetAmount(need.getTargetAmount());
        existing.setCurrentAmount(need.getCurrentAmount());
        existing.setItemRequired(need.getItemRequired());
        existing.setRequiredQuantity(need.getRequiredQuantity());
        existing.setReceivedQuantity(need.getReceivedQuantity());
        existing.setStatus(need.getStatus());

        return needRepository.save(existing);
    }

    public void deleteNeed(Long id) {
        needRepository.deleteById(id);
    }

    // Step 2 - progress tracking per need
    public Map<String, Object> getNeedProgress(Long id) {

        Need need = needRepository.findById(id).orElseThrow();

        Map<String, Object> progress = new HashMap<>();
        double percentage = 0;

        if (need.getType() != null && need.getType().equalsIgnoreCase("MONEY")) {

            double current = need.getCurrentAmount() == null ? 0 : need.getCurrentAmount();
            double target = need.getTargetAmount() == null ? 0 : need.getTargetAmount();

            if (target > 0) {
                percentage = (current / target) * 100;
            }

            progress.put("currentAmount", current);
            progress.put("targetAmount", target);

        } else {

            int received = need.getReceivedQuantity() == null ? 0 : need.getReceivedQuantity();
            int required = need.getRequiredQuantity() == null ? 0 : need.getRequiredQuantity();

            if (required > 0) {
                percentage = ((double) received / required) * 100;
            }

            progress.put("receivedQuantity", received);
            progress.put("requiredQuantity", required);
        }

        progress.put("percentage", Math.round(percentage * 100.0) / 100.0);
        progress.put("status", need.getStatus());

        return progress;
    }
}