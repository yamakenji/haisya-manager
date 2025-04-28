package com.example.haisya_manager.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.haisya_manager.entity.Ride;
import com.example.haisya_manager.entity.RideChildEntry;
import com.example.haisya_manager.entity.RideMemberEntry;
import com.example.haisya_manager.repository.RideChildEntryRepository;
import com.example.haisya_manager.repository.RideMemberEntryRepository;
import com.example.haisya_manager.repository.RideRepository;

@Service
public class RideService {
	private final RideRepository rideRepository;
	private final RideMemberEntryRepository rideMemberEntryRepository;
	private final RideChildEntryRepository rideChildEntryRepository;
	
	public RideService(RideRepository rideRepository, RideMemberEntryRepository rideMemberEntryRepository, RideChildEntryRepository rideChildEntryRepository) {
		this.rideRepository = rideRepository;
		this.rideMemberEntryRepository = rideMemberEntryRepository;
		this.rideChildEntryRepository = rideChildEntryRepository;
	}
	
	// 	ログイン中のadminIdに紐づく配車状況日付が新しい順にページングされた状態で取得する
	public Page<Ride> findRidesByAdminIdOrderByDateDesc(Integer adminId, Pageable pageable) {
		return rideRepository.findByAdminIdOrderByDateDesc(adminId, pageable);
	}
	
	// ログイン中のadmin_idが指定した日付を取得する
	public Page<Ride> findRidesByAdminAndDate(Integer adminId, LocalDate date, Pageable pageable) {
		return rideRepository.findByAdminIdAndDate(adminId, date, pageable);
	}
	
	// 指定したidを持つ配車を取得する
	public Optional<Ride> findRideById(Integer rideId) {
		return rideRepository.findById(rideId);
	}
	
	// 指定したride_idに紐づく配車指定をリストで取得する
	public List<RideMemberEntry> findMemberIdsByRideId(Integer rideId) {
		return rideMemberEntryRepository.findByRideId(rideId);
	}
	
	// 指定したride_idに紐づく乗員する子供をリストで取得する
	public List<RideChildEntry> findChildIdsByRideId(Integer rideId) {
		return rideChildEntryRepository.findByRideId(rideId);
	}

}
