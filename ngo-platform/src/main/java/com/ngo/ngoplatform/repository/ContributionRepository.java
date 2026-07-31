package com.ngo.ngoplatform.repository;

import com.ngo.ngoplatform.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    List<Contribution> findByDonorId(Long donorId);

}