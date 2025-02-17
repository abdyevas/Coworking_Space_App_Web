package com.coworking.repository;

import com.coworking.model.Spaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpacesRepository extends JpaRepository<Spaces, Integer> {}
