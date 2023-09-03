package com.vti.booking_tour.repository;

import com.vti.booking_tour.entities.Role;
import com.vti.booking_tour.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by quan0
 * Date 9/3/2023 - 3:48 PM
 * Description: ...
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findbyName(ERole name);
}
