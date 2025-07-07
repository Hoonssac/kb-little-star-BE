package com.kbstar.littlestar.moneytracker.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionDto {
	private int id;
	private Date date;
	private boolean isIncome;
	private double amount;
	private String memo;
	private int categoryId;
	private int userId;
}
