package com.example.haisya_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.haisya_manager.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

	// 指定したride_idに紐づく乗員する運転手をリストで取得する
	public List<Driver> findByRideId(Integer rideId);
}
