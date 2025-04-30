package com.example.haisya_manager.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.haisya_manager.entity.Child;
import com.example.haisya_manager.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RideEditForm {
	@NotNull(message = "日付を選択してください。")
	private LocalDate date;

	@NotBlank(message = "行き先を入力してください。")
	private String destination;
	
	private Member member;
	
	private Child child;
	
	private String memo;

}
