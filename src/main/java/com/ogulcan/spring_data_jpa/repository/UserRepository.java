package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
