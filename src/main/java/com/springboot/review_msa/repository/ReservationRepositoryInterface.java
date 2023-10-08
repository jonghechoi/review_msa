package com.springboot.review_msa.repository;

import com.springboot.review_msa.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryInterface extends JpaRepository<Reservation, String> {
    Reservation findByRid(String rid);
}
