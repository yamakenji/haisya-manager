package com.example.haisya_manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.haisya_manager.entity.RideEntry;

public interface RideEntryRepository extends JpaRepository<RideEntry, Integer> {

}