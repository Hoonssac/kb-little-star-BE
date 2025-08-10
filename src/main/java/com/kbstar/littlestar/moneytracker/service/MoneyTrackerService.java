package com.kbstar.littlestar.moneytracker.service;

import com.kbstar.littlestar.user.domain.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kbstar.littlestar.moneytracker.dto.CategoryDto;
import com.kbstar.littlestar.moneytracker.dto.TransactionDto;
import com.kbstar.littlestar.moneytracker.mapper.MoneyTrackerMapper;
import com.kbstar.littlestar.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MoneyTrackerService {

	private final MoneyTrackerMapper moneyTrackerMapper;
	private final UserService userService;

	public List<CategoryDto> getCategories(int userId) {
		return moneyTrackerMapper.getCategories(userId);
	}

	public CategoryDto addCategory(CategoryDto category) {
		moneyTrackerMapper.addCategory(category);
		return category;
	}

	public List<TransactionDto> getTransactions(int userId) {
		return moneyTrackerMapper.getTransactions(userId);
	}

	public TransactionDto addTransaction(TransactionDto transaction, HttpSession session) {
		moneyTrackerMapper.addTransaction(transaction);
		User user = (User)session.getAttribute("user");
		userService.addMileage(user.getUsername(), 500);
		return transaction;
	}

	public TransactionDto editTransaction(int transactionId, TransactionDto transaction) {
		transaction.setId(transactionId);
		moneyTrackerMapper.editTransaction(transaction);
		return transaction;
	}

	public void deleteTransaction(int transactionId) {
		moneyTrackerMapper.deleteTransaction(transactionId);
	}
}
