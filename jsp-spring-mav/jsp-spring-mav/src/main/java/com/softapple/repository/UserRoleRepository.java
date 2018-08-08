package com.softapple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softapple.model.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	List<UserRole> findByUsername(String username);

}
