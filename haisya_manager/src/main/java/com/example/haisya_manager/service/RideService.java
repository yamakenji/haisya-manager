package com.example.haisya_manager.service;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.haisya_manager.entity.Ride;
import com.example.haisya_manager.repository.RideRepository;

@Service
public class RideService {
	private final RideRepository rideRepository;
	
	public RideService(RideRepository rideRepository) {
		this.rideRepository = rideRepository;
	}
	
	// 	ログイン中のadminIdに紐づく配車状況日付が新しい順にページングされた状態で取得する
	public Page<Ride> findRidesByAdminIdOrderByDateDesc(Integer adminId, Pageable pageable) {
		return rideRepository.findByAdminIdOrderByDateDesc(adminId, pageable);
	}
	
	// ログイン中のadmin_idが指定した日付を取得する
	public Page<Ride> findRidesByAdminAndDate(Integer adminId, LocalDate date, Pageable pageable) {
		return rideRepository.findByAdminIdAndDate(adminId, date, pageable);
	}

}
