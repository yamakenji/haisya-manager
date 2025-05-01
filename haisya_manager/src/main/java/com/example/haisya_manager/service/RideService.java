package com.example.haisya_manager.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.haisya_manager.entity.Child;
import com.example.haisya_manager.entity.Ride;
import com.example.haisya_manager.entity.RideChildEntry;
import com.example.haisya_manager.entity.RideEntry;
import com.example.haisya_manager.entity.RideMemberEntry;
import com.example.haisya_manager.form.RideEditForm;
import com.example.haisya_manager.form.RideRegisterForm;
import com.example.haisya_manager.repository.ChildRepository;
import com.example.haisya_manager.repository.RideChildEntryRepository;
import com.example.haisya_manager.repository.RideEntryRepository;
import com.example.haisya_manager.repository.RideMemberEntryRepository;
import com.example.haisya_manager.repository.RideRepository;
import com.example.haisya_manager.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideService {
	private final RideRepository rideRepository;
	private final RideMemberEntryRepository rideMemberEntryRepository;
	private final RideChildEntryRepository rideChildEntryRepository;
	private final RideEntryRepository rideEntryRepository;
	private final ChildRepository childRepository;
	
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
	
	// 指定したride_idに紐づきtrueのみの保護者をリストで取得する
	public List<RideEntry> findMemberIdsByRideIdAndCanDriveTrue(Integer rideId) {
		return rideEntryRepository.findByRideIdAndCanDriveTrue(rideId);
	}
	
	// admin_idに紐づく子供をリストで取得する
	public List<Child> findChildIdsByAdminId(Integer adminId) {
		return childRepository.findByAdminId(adminId);
	}
	
	// 配車日、行き先、メモを登録する
	@Transactional
	public void createRide(UserDetailsImpl userDetailsImpl,
						   RideRegisterForm rideRegisterForm) {
		Ride ride = new Ride();
		
		ride.setDate(rideRegisterForm.getDate());
		ride.setDestination(rideRegisterForm.getDestination());
		ride.setMemo(rideRegisterForm.getMemo());
		ride.setAdmin(userDetailsImpl.getAdmin());
		
		rideRepository.save(ride);
	}
	
	// 配車号と子供を編集して登録する　行き先、メモも編集できるようにする
	@Transactional
	public void updateRide(RideEditForm rideEditForm, Ride ride,
						   RideMemberEntry rideMemberEntry,
						   RideChildEntry rideChildEntry) {
		
		ride.setDestination(rideEditForm.getDestination());
		rideMemberEntry.setRide(ride);
		rideMemberEntry.setMember(rideEditForm.getMember());
		rideChildEntry.setRide(ride);
		rideChildEntry.setChild(rideEditForm.getChild());
		rideChildEntry.setRideMemberEntry(rideMemberEntry);
		ride.setMemo(rideEditForm.getMemo());
		
		rideRepository.save(ride);
		rideMemberEntryRepository.save(rideMemberEntry);
		rideChildEntryRepository.save(rideChildEntry);
	}
	
	// 配車を削除する
	@Transactional
	public void deleteRide(Ride ride) {
		rideRepository.delete(ride);
	}

}
