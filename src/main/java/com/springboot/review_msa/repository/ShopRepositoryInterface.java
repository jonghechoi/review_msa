package com.springboot.review_msa.repository;

import com.springboot.review_msa.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepositoryInterface extends JpaRepository<Shop, String> {
    Shop findBySid(String sid);
}
