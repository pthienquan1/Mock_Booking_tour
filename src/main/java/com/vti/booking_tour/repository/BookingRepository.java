package com.vti.booking_tour.repository;

import com.vti.booking_tour.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by quan0
 * Date 8/29/2023 - 9:58 PM
 * Description: ...
 */
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByPhone(String phone);
    Optional<Booking> findByUsername(String username);
}
