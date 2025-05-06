package com.example.haisya_manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.haisya_manager.entity.Member;
import com.example.haisya_manager.security.UserDetailsImpl;
import com.example.haisya_manager.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/members")
public class AdminMemberController {
	private final MemberService memberService;
	
	// ログイン中ユーザーの保護者一覧ページを表示する
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
						@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
						@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
						RedirectAttributes redirectAttributes,
						Model model)
	{
		Page<Member> memberPage;
		Integer adminId = userDetailsImpl.getAdmin().getId();
		
		// 名前が検索された場合名前を表示する
		if (keyword != null) {
			memberPage = memberService.findMembersByAdminIdAndNameLike(adminId, keyword, pageable);
		} else {
			memberPage = memberService.findMembersByAdminId(adminId, pageable);
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("memberPage", memberPage);
		return "admin/members/index";
	}

}
