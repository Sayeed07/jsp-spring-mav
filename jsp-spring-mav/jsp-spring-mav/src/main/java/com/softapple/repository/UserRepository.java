package com.softapple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softapple.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsernameAndPasswordAndEnabled(String username, String password, boolean b);

}
