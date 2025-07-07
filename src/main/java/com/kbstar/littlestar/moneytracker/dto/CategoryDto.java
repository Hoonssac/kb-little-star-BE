package com.kbstar.littlestar.moneytracker.dto;

import lombok.Data;

@Data
public class CategoryDto {
	private int id;
	private String name;
	private boolean isIncome;
	private int userId;
}
