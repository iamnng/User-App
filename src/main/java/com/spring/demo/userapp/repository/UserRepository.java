package com.spring.demo.userapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.demo.userapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
