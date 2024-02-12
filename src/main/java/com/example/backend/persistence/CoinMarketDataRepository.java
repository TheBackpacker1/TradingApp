package com.example.backend.persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinMarketDataRepository extends JpaRepository < CoinMarketData, Integer>{

}
