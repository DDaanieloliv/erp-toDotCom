package com.example.erp_todotcom.domain.repository;

import com.example.erp_todotcom.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName( String name );
}
