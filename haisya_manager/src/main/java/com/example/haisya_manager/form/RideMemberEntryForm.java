package com.example.haisya_manager.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideMemberEntryForm {
	private String memberName;
	
	private List<Integer> childIds;

}
