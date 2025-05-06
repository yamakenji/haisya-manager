package com.example.haisya_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.haisya_manager.entity.RideChildEntry;

public interface RideChildEntryRepository extends JpaRepository<RideChildEntry, Integer> {
	
	// 指定したride_idに紐づく乗員する子供をリストで取得する
	public List<RideChildEntry> findByRideId(Integer rideId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM RideChildEntry r WHERE r.ride.id = :rideId")
	void deleteByRideId(@Param("rideId") Integer rideId);
}
