package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
