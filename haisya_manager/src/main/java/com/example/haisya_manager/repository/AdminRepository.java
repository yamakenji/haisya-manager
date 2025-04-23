package com.example.haisya_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.haisya_manager.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByUsername(String username);
}
