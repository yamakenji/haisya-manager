package com.example.haisya_manager.entity;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Integer id;

	 @Column(name = "username")
	 private String userName;
	 
	 @Column(name = "role")
	 private String role;
	 
	 @Column(name = "created_at", insertable = false, updatable = false)
	 private Timestamp createdAt;
	 
	 @Column(name = "updated_at", insertable = false, updatable = false)
	 private Timestamp updatedAt;
	 
}
