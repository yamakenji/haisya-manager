package com.example.haisya_manager.controller;

import java.time.LocalDate;

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

import com.example.haisya_manager.entity.Ride;
import com.example.haisya_manager.security.UserDetailsImpl;
import com.example.haisya_manager.service.RideService;

@Controller
@RequestMapping("/admin/rides")
public class AdminRideController {
	private final RideService rideService;
	
	public AdminRideController(RideService rideService) {
		this.rideService = rideService;
	}
	// ログイン中ユーザーの配車一覧ページを表示する
	@GetMapping
	public String index(@RequestParam(name = "date", required = false) LocalDate date,
						@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
						@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable,
						RedirectAttributes redirectAttributes,
						Model model) {
		Page<Ride> ridePage;
		Integer adminId = userDetailsImpl.getAdmin().getId();
		
		if (date != null) {
			ridePage = rideService.findRidesByAdminAndDate(adminId, date, pageable);
		} else {
			ridePage = rideService.findRidesByAdminIdOrderByDateDesc(adminId, pageable);
		}
		
		if (ridePage.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "指定された日の配車状況が存在しません。");
			return "redirect:/admin/rides";
		}
		
		model.addAttribute("date", date);
		model.addAttribute("ridePage", ridePage);
		
		return "admin/rides/index";
	}
}
