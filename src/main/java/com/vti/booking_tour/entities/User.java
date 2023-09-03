package com.vti.booking_tour.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quan0
 * Date 8/31/2023 - 7:19 PM
 * Description: ...
 */
public class User {
    private Long id;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDateTime birthday;
    private String address;
    private int status;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
