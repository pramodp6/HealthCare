package com.health.pramod.HealthCare.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.health.pramod.HealthCare.utility.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class Patients implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "firstname",nullable = false)
    private String firstname;
    @Column(name = "lastname",nullable = false)
    private String lastname;
    @Column(name = "gender",nullable = false)
    private String gender;
    @NotBlank(message = "Email Is Mandatory")
    @Email
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "dob",nullable = false)
    private String dob;



    @Column(name = "password",nullable = false)
    private String password;
    @Column(nullable = true)
    private LocalDate passwordLastUpdated;
    @Column(nullable = true)
    private LocalDate credentialsExpirationDate;;





    @Column(name = "contact",nullable = false)
    private String contact;
    @Column(name = "address",nullable = false)
    private String address;
    private Role role;
    private String avatar;

    @Column(name = "create_at")
    private LocalDateTime createAt;
    private boolean enabled;
    @Column(name = "update_at")
    private LocalDateTime updateAt;




   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new  SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }




}
