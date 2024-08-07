package com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.GstRate;

import java.util.Optional;

@Repository
public interface GstRateRepository extends JpaRepository<GstRate, Long> {
	Optional<GstRate> findByCategory(String category);
}
