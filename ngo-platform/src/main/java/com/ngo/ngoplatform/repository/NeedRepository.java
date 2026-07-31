package com.ngo.ngoplatform.repository;

import com.ngo.ngoplatform.entity.Need;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedRepository extends JpaRepository<Need, Long> {
}