package com.example.haisya_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.haisya_manager.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	
}
