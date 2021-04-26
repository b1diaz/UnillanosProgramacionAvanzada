package com.example.repositories;

import com.example.entities.typePainting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface typePaintingRepository extends JpaRepository<typePainting, Integer >  {

}
