package com.example.haisya_manager.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "ride_member_entry")
@Data
@ToString(exclude = "rideChildEntries")
public class RideMemberEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ride_id")
	private Ride ride;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	 
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;

	@OneToMany(mappedBy = "rideMemberEntry", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<RideChildEntry> rideChildEntries;
}
