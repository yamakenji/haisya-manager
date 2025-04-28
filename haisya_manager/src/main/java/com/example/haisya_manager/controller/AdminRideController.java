package com.example.haisya_manager.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.haisya_manager.entity.Ride;
import com.example.haisya_manager.entity.RideChildEntry;
import com.example.haisya_manager.entity.RideMemberEntry;
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
	
	// 配車詳細を表示する
	@GetMapping("/{rideId}")
	public String show(@PathVariable(name = "rideId") Integer rideId,
					   @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
					   RedirectAttributes redirectAttributes,
					   Model model)
	{
		Optional<Ride> optionalRide = rideService.findRideById(rideId);
		List<RideMemberEntry> rideMemberEntries = rideService.findMemberIdsByRideId(rideId);
		List<RideChildEntry> rideChildEntries = rideService.findChildIdsByRideId(rideId);
		
		if (optionalRide.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "配車が存在しません。");
			return "redirect:/admin/rides";
		}
		
		Ride ride = optionalRide.get();
		
		if (!ride.getAdmin().getId().equals(userDetailsImpl.getAdmin().getId())) {
			redirectAttributes.addFlashAttribute("errorMessage", "指定されたユーザーでログインしてください");
			return "redirect:/admin/rides";
		}
		
		model.addAttribute("rideChildEntries", rideChildEntries);
		model.addAttribute("rideMemberEntries", rideMemberEntries);
		model.addAttribute("ride", ride);
		
		return "admin/rides/show";
	}
}
