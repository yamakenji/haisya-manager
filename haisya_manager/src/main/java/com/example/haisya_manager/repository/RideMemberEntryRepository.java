package com.example.haisya_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.haisya_manager.entity.RideMemberEntry;

public interface RideMemberEntryRepository extends JpaRepository<RideMemberEntry, Integer> {

	// 指定したride_idに紐づく配車指定をリストで取得する
	public List<RideMemberEntry> findByRideId(Integer rideId);
}
