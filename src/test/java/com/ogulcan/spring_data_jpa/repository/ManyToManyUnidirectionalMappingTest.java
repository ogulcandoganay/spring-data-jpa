package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Role;
import com.ogulcan.spring_data_jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUnidirectionalMappingTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstName("Ramesh");
        user.setLastName("Abi");
        user.setEmail("rameshabi@mail.com");
        user.setPassword("secrete");

        Role role = new Role();
        role.setName("ROLE_ADMIN");

        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        user.getRoles().add(role);
        user.getRoles().add(customer);

        userRepository.save(user);
    }

    @Test
    void UpdateUser() {
        User user = userRepository.findById(1L).get();
        user.setLastName("Fadatare");
        user.setPassword("s3cr3t");
        Role role = new Role();
        role.setName("ROLE_USER");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Test
    void fetchUser(){
        User user = userRepository.findById(1L).get();
        System.out.println(user.getEmail());
        user.getRoles().forEach((role) -> {
            System.out.println(role.getName());
        });
    }

    @Test
    void deleteUser(){
       userRepository.deleteById(1L);
    }
}
