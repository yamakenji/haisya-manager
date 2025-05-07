package com.example.haisya_manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.haisya_manager.entity.Child;
import com.example.haisya_manager.entity.Member;
import com.example.haisya_manager.repository.ChildRepository;
import com.example.haisya_manager.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final ChildRepository childRepository;
	
	// ログイン中のadminIdに紐づくメンバー一覧がページングされた状態で取得する
	public Page<Member> findMembersByAdminId(Integer adminId, Pageable pageable) {
		return memberRepository.findByAdminId(adminId, pageable);
	}
	
	// ログイン中のadminIdに紐づき、指定された名前を含むmemberをページングされた状態で取得する。
	public Page<Member> findMembersByAdminIdAndNameLike(Integer adminId, String keyword, Pageable pageable) {
		return memberRepository.findByAdminIdAndNameLike(adminId, "%" + keyword + "%", pageable);
	}
	
	// 指定したidを持つ保護者を取得する
	public Optional<Member> findMemberById(Integer adminId) {
		return memberRepository.findById(adminId);
	}
	
	// member_idに紐づく子供をリストで取得する
	public List<Child> findChildrenByMemberId(Integer memberId) {
		return childRepository.findByMemberId(memberId);
	}

}
