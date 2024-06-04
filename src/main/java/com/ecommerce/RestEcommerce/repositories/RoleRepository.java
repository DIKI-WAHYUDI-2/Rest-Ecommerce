package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
