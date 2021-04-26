package com.example.repositories;

import com.example.entities.categoryDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryDamageRepository extends JpaRepository<categoryDamage, Integer > {
}
