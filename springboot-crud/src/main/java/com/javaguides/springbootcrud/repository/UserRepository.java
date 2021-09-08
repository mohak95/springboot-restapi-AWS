package com.javaguides.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.springbootcrud.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
