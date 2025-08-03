package com.kbstar.littlestar.moneytracker.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionDto {
	private int id;
	private Date date;

	@JsonProperty("is_income")
	private boolean isIncome;

	private double amount;
	private String memo;

	@JsonProperty("category_id")
	private int categoryId;

	@JsonProperty("user_id")
	private int userId;
}
