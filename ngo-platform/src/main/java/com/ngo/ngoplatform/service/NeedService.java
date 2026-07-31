package com.ngo.ngoplatform.service;

import com.ngo.ngoplatform.entity.Need;
import com.ngo.ngoplatform.repository.NeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}