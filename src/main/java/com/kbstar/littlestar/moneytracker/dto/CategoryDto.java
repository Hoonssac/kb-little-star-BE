package com.kbstar.littlestar.moneytracker.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CategoryDto {
	private Long id;  // ✅ 이 필드 추가!

	private String name;

	@JsonProperty("is_income")
	private Boolean isIncome;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("transaction_ids")
	private List<Long> transactionIds;
}