package com.example.haisya_manager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.haisya_manager.entity.Member;
import com.example.haisya_manager.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	
	// ログイン中のadminIdに紐づくメンバー一覧がページングされた状態で取得する
	public Page<Member> findMembersByAdminId(Integer adminId, Pageable pageable) {
		return memberRepository.findByAdminId(adminId, pageable);
	}
	
	// ログイン中のadminIdに紐づき、指定された名前を含むmemberをページングされた状態で取得する。
	public Page<Member> findMembersByAdminIdAndNameLike(Integer adminId, String keyword, Pageable pageable) {
		return memberRepository.findByAdminIdAndNameLike(adminId, "%" + keyword + "%", pageable);
	}

}
