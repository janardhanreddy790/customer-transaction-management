package com.nagam.poc.customer_transaction_management.repository;

import com.nagam.poc.customer_transaction_management.model.RoleNames;
import com.nagam.poc.customer_transaction_management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleNames name);
}
