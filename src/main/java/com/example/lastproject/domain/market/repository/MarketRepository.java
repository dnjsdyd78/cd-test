package com.example.lastproject.domain.market.repository;

import com.example.lastproject.domain.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market,Long> {
}
